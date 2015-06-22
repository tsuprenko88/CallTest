import org.testng.Reporter;
import org.testng.annotations.Test;

import utility.Utils;


public class Calculator extends Utils {
		
	@Test
	public void calculations() throws Exception {
		xlPath = "excel-input//Calculations.xlsx";
		xlSheetName = "data";
		xlWritePath = "excel-output//"+xlSheetName+".xls";
		
		xlRead(xlPath, xlSheetName);
		
		setUp();
		
		for (int i = 1; i < xlRows; i++) {
			
			number = localArray[i][0];
			firstNumber = localArray[i][1];
			secondNumber = localArray[i][2];
			action = localArray[i][3];
			expResult = localArray[i][4];
			
			arithmeticAction(firstNumber, secondNumber, action);
			
			localArray[i][5] = actResult;
			
			if (!expResult.equals(actResult)) {
				fail = true;
				failName.add(number + ") Wrong result");
				localArray[i][6] = "FAIL";
			}
			else {
				localArray[i][6] = "Pass";
				Reporter.log(number + ") Correct result", true);
			}
			
			xlWrite(xlWritePath, xlSheetName, localArray);
					
		}
		
		quit();
	
	}
	
	

}
