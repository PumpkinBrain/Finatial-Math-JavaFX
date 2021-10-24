package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Logic {
	Data dat = new Data();
	
//convert tax to decimal values
//check and convert time measurement
	protected void checkTime(char time, char tax) {
		dat.setI(String.valueOf(dat.getI() / 100));
		if(time != tax) {
			switch(tax) {
			case 'd':
				if(time == 'm') {
					dat.setN(String.valueOf(dat.getN() / 30));
				} else {
					dat.setN(String.valueOf(dat.getN() / 360));
				}
			break;
			case 'm':
				if(time == 'd') {
					dat.setN(String.valueOf(dat.getN() / 30));
				} else {
					dat.setN(String.valueOf(dat.getN() / 12));
				}
			break;
			case 'y':
				if(time == 'd') {
					dat.setN(String.valueOf(dat.getN() / 360));
				} else {
					dat.setN(String.valueOf(dat.getN() / 12));
				}
			break;
			}
		}
	}
	
//call time conversion method then check for unknown values and execute corresponding formula
	protected void showResultSimple(Label display) {
		
		if(Data.state == 6) {
			checkTime(dat.getTime(), dat.getTax());
			if(dat.isBoolPv()) {
				display.setText("Pv: " + dat.simplePv());
			} else if(dat.isBoolI()) {
				display.setText("I: " + dat.getI());
			} else if(dat.isBoolN()) {
				display.setText("N: " + dat.simpleN());
			} else {
				display.setText("Fv: " + dat.simpleFv()); 
			}
		}
	}

	protected void showResultCompound(Label display) {
		if(Data.state == 6) {
			checkTime(dat.getTime(), dat.getTax());
			if(dat.isBoolPv()) {
				display.setText("Pv: " + dat.compoundPv());
			} else if(dat.isBoolI()) {
				display.setText("I: " + dat.compoundI());
			} else if(dat.isBoolN()) {
				display.setText("N: " + dat.compoundN());
			} else {
				display.setText("Fv: " + dat.compoundFv());
			}
		}
	}
	
//set values according to current sate
//states 2 and 4 will be responsible for the time measurements, and 5 for selecting either simple or compound interest
//2, 4 and 5 will be under responsability of their respective buttons, as the rest will be under "okButton"
	protected void userInput(TextField userText, Label display) {
		switch(Data.state) {
		case 0:
			dat.setPv(userText.getText());
			userText.setText(null);
		break;
		case 1:
			dat.setI(userText.getText());
			display.setText("Insira a taxa em %");
			userText.setText(null);
		break;
		case 2:
			display.setText("Escolha a medida de tempo da taxa");
			userText.setText(null);
		break;
		case 3:
			dat.setN(userText.getText());
			display.setText("Insira o período");
			userText.setText(null);
		break;
		case 4:
			display.setText("Escolha a medida de tempo do período");
			userText.setText(null);
		break;
		case 5:
			if (Data.unknown) {
				display.setText("Insira o FV");
				dat.setFv(userText.getText());
				userText.setText(null);
			} else {
				Data.state++;
			}
		break;
		case 6:
			display.setText("Escolha juros simples ou compostos");
		break;
	
		}
	}
	
//will define measurement type and update state of application if the correct values are inserted
	protected void measurement(char measure) {
		if(Data.state == 2) {
			dat.setTax(measure);
			Data.state++;
		} else if (Data.state == 4) {
			dat.setTime(measure);
			Data.state++;
		}
	}
	
	protected void clear(Label display, TextField userInput) {
		Data.state = 0;
		display.setText("Insira o Pv");
		userInput.setText(null);
	}
}
