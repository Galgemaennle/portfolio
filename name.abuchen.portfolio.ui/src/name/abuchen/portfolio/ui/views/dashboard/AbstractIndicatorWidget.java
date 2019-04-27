package name.abuchen.portfolio.ui.views.dashboard;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import name.abuchen.portfolio.model.Dashboard.Widget;
import name.abuchen.portfolio.util.TextUtil;

public abstract class AbstractIndicatorWidget<D> extends WidgetDelegate<D>
{
    protected Label title;
    protected Label indicator;

    public AbstractIndicatorWidget(Widget widget, DashboardData dashboardData)
    {
        super(widget, dashboardData);
    }

    @Override
    public Composite createControl(Composite parent, DashboardResources resources)
    {
        Composite container = new Composite(parent, SWT.NONE);
        container.setBackground(parent.getBackground());
        GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).applyTo(container);
    
        title = new Label(container, SWT.NONE);
        title.setText(TextUtil.tooltip(getWidget().getLabel()));
        title.setBackground(container.getBackground());
        GridDataFactory.fillDefaults().grab(true, false).applyTo(title);
    
        indicator = new Label(container, SWT.NONE);
        indicator.setFont(resources.getKpiFont());
        indicator.setBackground(container.getBackground());
        indicator.setText(""); //$NON-NLS-1$
        GridDataFactory.fillDefaults().grab(true, false).applyTo(indicator);
    
        return container;
    }

    @Override
    public Control getTitleControl()
    {
        return title;
    }

    @Override
    public void update(D data)
    {
        this.title.setText(TextUtil.tooltip(getWidget().getLabel()));
    }
    
    @Override    
    public void updateLabel()
    {       
        String label = WidgetFactory.valueOf(getWidget().getType()).getLabel() + ", " + get(DataSeriesConfig.class).getDataSeries().getLabel(); //$NON-NLS-1$
        getWidget().setLabel(label);
    }
}
