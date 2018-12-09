package pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import character.Character;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class CharacterPane extends GridPane {
	private ArrayList<Character> characters;
	private ArrayList<CharacterField> characterFields;
	private ArrayList<ImageView> imageViews;
	private ArrayList<ImageView> deadImageViews;
	
	private ImageView burnImage;

	public CharacterPane(ArrayList<Character> characters) {
		setAlignment(Pos.CENTER);
		this.characters = characters;
		getStylesheets().add(getClass().getResource("\\..\\logic\\application.css").toExternalForm());
		setPrefWidth(450);
		setPadding(new Insets(15));
		setVgap(10);

		characterFields = new ArrayList<CharacterField>();
		imageViews = new ArrayList<ImageView>();
		deadImageViews = new ArrayList<ImageView>();
		
		burnImage = new ImageView();
		setBurnImage("resources/fire.gif");
		burnImage.setOpacity(0.5);
		burnImage.setTranslateX(125);
		
		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setBackground(new Background(new BackgroundFill(Color.IVORY, null, null)));
		for (int i = 0; i < characters.size(); i++) {
			CharacterField characterField = new CharacterField(characters.get(i));
			characterFields.add(characterField);
			imageViews.add(characters.get(i).getImage());
			deadImageViews.add(characters.get(i).getDeadImage());
			add(characters.get(i).getDeadImage(), 0, 2 * i);
			add(characters.get(i).getImage(), 0, 2 * i);

			add(characterField, 0, 2 * i + 1);
		}
		
		add(burnImage, 0, 0);
	}
	
	public void setBurnImage(String url) {
		FileInputStream input;
		try {
			input = new FileInputStream(url);
			Image image = new Image(input, 150, 150, false, false);
			burnImage = new ImageView(image);
			System.out.println("test");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Character> getCharacters() {
		return this.characters;
	}

	public void update() {
		for (int i = 0; i < 3; i++) {
			if (characters.get(i).isDead()) {
				imageViews.get(i).setVisible(false);
			}
		}
	}

	public boolean isDefeated() {
		for (int i = 0; i < characters.size(); i++) {
			if (!characters.get(i).isDead())
				return false;
		}
		return true;
	}

	public ArrayList<CharacterField> getCharacterFields() {
		return this.characterFields;
	}
}
