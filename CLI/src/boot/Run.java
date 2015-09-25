package boot;

import java.util.HashMap;
import java.util.Scanner;

import controller.Command;
import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;
/**
 * Main class, gets client's input and output stream paths ,runs the CLI's thread.
 *The CLI uses MVC architectural pattern:
 *When commands are retrieved from input stream, they run through the controller,
 *then they are being processed in the model layer. when done, some info
 *returns to the controller and then finally gets back to the view layer, to the client.
 */
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
        //C:\Users\Timi\Desktop\input.txt
    }
}
