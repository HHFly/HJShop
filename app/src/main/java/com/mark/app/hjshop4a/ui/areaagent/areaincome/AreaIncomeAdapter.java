package com.mark.app.hjshop4a.ui.areaagent.areaincome;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
/**
 * Created by pc on 2018/4/21.
 */

public class AreaIncomeAdapter extends BaseListRvAdapter <AreaIncome> {

    public AreaIncomeAdapter(List<AreaIncome> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.activity_areaincome;
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPos, AreaIncome data) {
        LineChartView incomeLineMonth = holder.get(R.id.income_line_month);
        List<OrderItemBean> monthList = data == null ? null : data.getOrderMonthList();
        initTable(incomeLineMonth, monthList, "");
        boolean isMonthDataNull = monthList == null || monthList.isEmpty();
        holder.visibility(R.id.stats_layout_month, !isMonthDataNull);
    }

    /**
     * 初始化数据报表
     *
     * @param data
     */
    private void initTable(LineChartView view, List<OrderItemBean> data, String title) {
        if (view == null) {
            return;
        }

        List<PointValue> pointValues = new ArrayList<>();
        List<AxisValue> axisXValues = new ArrayList<>();
        int count = data == null ? 0 : data.size();
        for (int i = 0; i < count; i++) {
            OrderItemBean item = data.get(i);

//            String label = NumberUtils.getFormatDateTimeMD(item.getDay());
            String label = String.valueOf(item.getDay());
            float f = item.getNumber();

            AxisValue aValue = new AxisValue(i).setLabel(label);
            PointValue pValue = new PointValue(i, f);

            axisXValues.add(aValue);
            pointValues.add(pValue);
        }
        initLineChart(view, pointValues, axisXValues, title);
        boolean isDataNull = data == null || data.isEmpty();
//        setVisibilityGone(view, !isDataNull);
    }

    /**
     * 初始化数据报表配置
     *
     * @param lineChartView
     * @param pointValues
     * @param axisXValues
     * @param title
     */
    private void initLineChart(LineChartView lineChartView, List<PointValue> pointValues, List<AxisValue> axisXValues, String title) {
        Line line = new Line(pointValues).setColor(Color.parseColor("#598FFB"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
//	    line.setStrokeWidth(3);
        line.setFilled(false);//是否填充曲线的面积
//        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLabelsOnlyForSelected(true);///点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
//	    axisX.setTextColor(Color.WHITE);  /设置字体颜色
        axisX.setTextColor(Color.parseColor("#D6D6D9"));//设置字体颜色

//	    axisX.setName("data");  //表格名称
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(axisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
        axisX.setTextColor(Color.BLACK);
        if (!TextUtils.isEmpty(title)) {
            axisX.setName(title);
        }

        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setInside(true);
        axisY.setHasLines(true);
        axisY.setTextSize(11);//设置字体大小
        axisY.setTextColor(Color.BLACK);
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY); //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
//        incomeLineWeek.setMaxZoom((float) 3);//最大方法比例
        lineChartView.setZoomEnabled(false);
        lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);

        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */

        Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.left = 0;
        v.right = axisXValues.size();
        lineChartView.setCurrentViewport(v);
    }
}
