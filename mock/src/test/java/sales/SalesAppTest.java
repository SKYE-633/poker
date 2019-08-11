package sales;

import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JMockit.class)
public class SalesAppTest {

	@Test
	public void testIsEffective(){
		SalesApp salesApp = new SalesApp();
		Sales sales = mock(Sales.class);
		when(sales.getEffectiveFrom()).thenReturn(new Date(0));
		when(sales.getEffectiveTo()).thenReturn(new Date());
		Assert.assertEquals(true,salesApp.isEffective(sales));
	}


	@Test
	public void testGetSalesReportData() {
		SalesApp salesApp = new SalesApp();
		SalesReportData data1 = mock(SalesReportData.class);
		SalesReportData data2 = mock(SalesReportData.class);
		SalesReportDao salesReportDao = mock(SalesReportDao.class);
		Sales sales = mock(Sales.class);
		when(salesReportDao.getReportData(sales)).thenReturn(Arrays.asList(data1,data2));
		when(data1.isConfidential()).thenReturn(true);
		when(data2.isConfidential()).thenReturn(false);
		when(data1.getType()).thenReturn("SalesActivity");
		when(data2.getType()).thenReturn("SalesActivity");
		boolean isSupervisor = false;
		List<SalesReportData> filterList = salesApp.getSalesReportData(isSupervisor,salesReportDao,sales);
		Assert.assertEquals(2,filterList.size());
	}

	@Test
	public void testGetTempList(){
		SalesApp salesApp = spy(new SalesApp());
		List<SalesReportData> result = salesApp.getTempList(1,Arrays.asList(new SalesReportData(),new SalesReportData()));
		Assert.assertEquals(2,result.size());
	}

	@Test
	public void testGenerateSalesActivityReport() {
		SalesApp salesApp = spy(new SalesApp());
		Sales sales = spy(new Sales());

		new mockit.MockUp<SalesDao>() {
			@mockit.Mock
			public Sales getSalesBySalesId(String salesId) {
				return sales;
			}
		};

		new mockit.MockUp<SalesReportDao>() {
			@mockit.Mock
			public List<SalesReportData> getReportData(Sales sales) {
				return null;
			}
		};

		new mockit.MockUp<SalesApp>() {
			@mockit.Mock
			public List<SalesReportData> getFilterReportData(boolean isSupervisor, SalesReportDao salesReportDao, List<SalesReportData> filteredReportDataList, Sales sales) {
				return null;
			}
		};

		new mockit.MockUp<SalesApp>() {
			@mockit.Mock
			public List<SalesReportData> getTempList(int maxRow, List<SalesReportData> reportDataList) {
				return null;
			}
		};

		new mockit.MockUp<SalesApp>() {
			@mockit.Mock
			public SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
				return new SalesActivityReport();
			}
		};

		new mockit.MockUp<SalesActivityReport>() {
			@mockit.Mock
			public String toXml() {
				return "xml";
			}
		};
		doReturn(false).when(salesApp).isEffective(sales);

		try {
			salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}


