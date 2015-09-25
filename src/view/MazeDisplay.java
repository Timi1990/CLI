package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class MazeDisplay extends Canvas
{
    public MazeDisplay(Composite parent, int style)
    {
        super(parent, style);

        setBackground(new Color(null, 255, 255, 255));

        addPaintListener(new PaintListener()
        {
            @Override
            public void paintControl(PaintEvent e)
            {
                e.gc.setForeground(new Color(null,0,0,0));
                e.gc.setBackground(new Color(null, 0, 0, 0));

                int width = getSize().x;
                int height = getSize().y;



            }
        });
    }
}
