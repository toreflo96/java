import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.geometry.*;
import java.io.*;

public class Oblig7 extends Application {
  private Rute[][] ruter;
  private Label losninger;
  private Labyrint labyrint;
  private GridPane rutenett;
  private boolean[][] boolLabyrint;
  private Liste<String> ut;
  private BorderPane root = new BorderPane();
  private Stage stage = new Stage();

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    root.setTop(lagTopp());
    root.setCenter(rutenett);
    root.setBottom(lagBunn());
    
    stage.setScene(new Scene(root));
    stage.setTitle("LabyrintGUI");
    stage.show();
  }

  public HBox lagTopp() {
    TextField filFelt = new TextField();
    Button velgFil = new Button("Velg fil");
    velgFil.setOnAction(new EventHandler<ActionEvent> (){
      @Override
      public void handle(ActionEvent event){
        FileChooser filVelger = new FileChooser();
        filVelger.setTitle("Velg fil");
        File valgFil = filVelger.showOpenDialog(stage);
        if(valgFil != null){
          filFelt.setText(valgFil.getPath());
        }
      }
    });

    Button lastInn = new Button("Last inn fil");
    lastInn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        rutenett = new GridPane();
        rutenett.setAlignment(Pos.CENTER);
        File fil = new File(filFelt.getText());
        try {
          labyrint = labyrint.lesFraFil(fil);
          System.out.println(labyrint.toString());
          Rute[][] ruter = labyrint.returnerRutearray();
           for (int rad = 0; rad < labyrint.hoyde; rad++) {
             for (int kol = 0; kol < labyrint.bredde; kol++) {
               if (ruter[rad][kol].tilTegn() == '#') {
                 rutenett.add(new GUIRuteSvart(rad+1, kol+1), kol, rad);
               } else {
                 rutenett.add(new GUIRuteHvit(rad+1, kol+1), kol, rad);
               }
             }
           }
        } catch (FileNotFoundException e) {}
          root.setCenter(rutenett);
          stage.sizeToScene();
        }
    });

    return new HBox(velgFil, filFelt, lastInn);
  }

  public HBox lagBunn(){
    losninger = new Label();
    HBox retur = new HBox(losninger);
    return retur;
  }

  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while(m.find()) {
          int x = Integer.parseInt(m.group(1))-1;
          int y = Integer.parseInt(m.group(2))-1;
          losning[y][x] = true;
      }
      return losning;
  }

  public class GUIRute extends Rectangle {
    int str;
    int rad;
    int kol;

    public GUIRute(int str, int rad, int kol) {
      super(str, str);
      this.rad = rad;
      this.kol = kol;
      setStroke(Color.BLACK);
    }
  }

    public class GUIRuteHvit extends GUIRute {
      public GUIRuteHvit(int rad, int kol) {
        super(30, rad, kol);

        setFill(Color.WHITE);

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              ut = labyrint.finnUtveiFra(kol, rad);
              if (ut.storrelse() > 0) {
                losninger.setText("Antall losninger: "+ut.storrelse());
                boolLabyrint = losningStringTilTabell(ut.fjern(), labyrint.bredde, labyrint.hoyde);
                for (int y = 0; y < labyrint.hoyde; y++) {
                  for (int x = 0; x < labyrint.bredde; x++) {
                    if(boolLabyrint[y][x] == true) {
                      rutenett.add(new LosningRute(y+1, x+1), x,y );
                    }
                  }
                }
              }
          }
        });
      }
    }

    public class GUIRuteSvart extends GUIRute {
      public GUIRuteSvart(int rad, int kol) {
        super(30, rad, kol);
      }
    }

    public class LosningRute extends GUIRute {
      public LosningRute(int rad, int kol) {
        super(30, rad, kol);
        setFill(Color.GREEN);
      }
    }
}
