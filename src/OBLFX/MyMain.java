package OBLFX;
import javafx.application.Application;
import javafx.stage.Stage;


public class MyMain extends Application{

	public static void main(String[] args) throws Exception{
		
		 launch(args);
	}
	

		@Override
			public void start(Stage arg0) throws Exception {
			WelcomeScreenController wFrame = new WelcomeScreenController(); // create Welcome Screen
			wFrame.start(arg0);	
	}

}
