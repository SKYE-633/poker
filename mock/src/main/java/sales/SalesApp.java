package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
		SalesDao salesDao = new SalesDao();
		SalesReportDao salesReportDao = new SalesReportDao();

		List<String> headers = null;

		Sales sales = salesDao.getSalesBySalesId(salesId);
		if (salesId == null) {
			return;
		}

		List<SalesReportData> reportDataList = getSalesReportData(isSupervisor, salesReportDao, sales);
		List<SalesReportData> filteredReportDataList;
		List<SalesReportData> tempList = getTempList(maxRow, reportDataList);
		filteredReportDataList = tempList;

		SalesActivityReport report = this.generateReport(headers, reportDataList);

	}
    public List<String> setHeaders(boolean isNatTrade) {
        List<String> headers = null;
        if (isNatTrade) {
            headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
        } else {
            headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
        }
        return headers;
    }

    public void uploadDocument(SalesActivityReport report) {
        EcmService ecmService = new EcmService();
        ecmService.uploadDocument(report.toXml());
    }

	public List<SalesReportData> getTempList(int maxRow, List<SalesReportData> reportDataList) {
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		return tempList;
	}


	public List<SalesReportData> getSalesReportData(boolean isSupervisor, SalesReportDao salesReportDao, Sales sales) {
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();

		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}
		return reportDataList;
	}

	public boolean isEffective(Sales sales) {
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return false;
		}
		return true;
	}

	private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
