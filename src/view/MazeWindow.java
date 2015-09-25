package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

public class MazeWindow extends BasicWindow
{
    @Override
    void initWidgets()
    {
        shell.setLayout(new GridLayout(1, false));

        MazeDisplay maze = new MazeDisplay(shell, SWT.BORDER);
        maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }
}
