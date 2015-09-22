package boot;

import controller.Command;
import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

import java.util.HashMap;
import java.util.Scanner;

public class Run
{
    public static void main(String[] arg)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input file path...");
        String fileInput = scanner.next();

        System.out.println("Enter output file path...");
        String fileOutput = scanner.next();

        Model model = new MyModel();
        View view = new MyView();
        Controller controller = new MyController(model, view);

        HashMap<String, Command> commandHashMap = controller.createCommandNameToCommand();

        view.start(fileInput, fileOutput, commandHashMap);
    }
}
