package neurofeedback.api

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtilities
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset

class Grapher {

    static void generateGraphImage(String userTreatmentId, List<AnalyzedData> analyzedDatas) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset()
        String chartTitle = "EEGChart" + userTreatmentId

        for(ad in analyzedDatas) {
            for(Point point in ad.visualizedData) {
                dataset.addValue(point.x, "Voltage", point.y)
            }
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time","Voltage",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);


        try {
            OutputStream out = new FileOutputStream(chartTitle + ".png")
            ChartUtilities.writeChartAsPNG(out,
                    lineChart,
                    500,
                    500)

        } catch (IOException ex) {}
    }
}
