package model;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.*;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

import java.io.*;
import java.util.HashMap;

public class MyModel implements Model
{
    private final HashMap<String, Maze3d> mazeNameToMaze3d = new HashMap<String, Maze3d>();
    private final Maze3dGenerator maze3dGenerator = new MyMaze3dGenerator();

    @Override
    public void put(String mazeName, Maze3d maze3d)
    {
        mazeNameToMaze3d.put(mazeName, maze3d);
    }

    @Override
    public Maze3d getMazeBy(String mazeName)
    {
        return mazeNameToMaze3d.get(mazeName);
    }

    @Override
    public Maze3d generateMaze3dBy(Integer dimension, Integer rows, Integer columns)
    {
        MazeArgumentsForInit mazeArgumentsForInit = new MazeArgumentsForInit(dimension, rows, columns);
        return maze3dGenerator.generate(mazeArgumentsForInit);
    }

    @Override
    public Integer[][] getCrossSelectionBy(String zir, String mazeName, Integer index) throws IndexOutOfBoundsException
    {
        Maze3d maze3d = getMazeBy(mazeName);

        return getCrossSelectionBy(zir, maze3d, index);
    }

    private Integer[][] getCrossSelectionBy(String zir, Maze3d maze3d, Integer index) throws IndexOutOfBoundsException
    {
        switch (zir)
        {
            case "X":
            {
                return maze3d.getCrossSectionByX(index);
            }
            case "Y":
            {
                return maze3d.getCrossSectionByY(index);
            }
            case "Z":
            {
                return maze3d.getCrossSectionByZ(index);
            }
        }
        return null;
    }

    @Override
    public void save(String filePath, String mazeName) throws IOException
    {
        Maze3d maze3d = mazeNameToMaze3d.get(mazeName);

        OutputStream out = new MyCompressorOutputStream(new FileOutputStream(filePath));
        out.write(maze3d.toByteArray());
    }

    @Override
    public void load(String fileName, String mazeName) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        InputStream in = new MyDecompressorInputStream(fileInputStream);

        byte b[] = new byte[5000];
        in.read(b);
        in.close();

        Maze3d maze3d = new Maze3d(b);
        put(mazeName, maze3d);
    }

    @Override
    public Integer calcSizeOf(Maze3d maze3d)
    {
        return 0;
    }
}