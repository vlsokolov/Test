import java.util.ArrayList;
import java.util.List;

public class Pyramid {
	
	private static int countTriangles = 0;
	private static List<Triangle> modifiedList = new ArrayList<>();
	
	static void pasreInputString(String input) throws Exception{
		if(input.length()%4 == 1){
			throw new Exception("Invalid string length");
		} else {
			countTriangles = input.length()/4;
			buildMainPyramid(input);
		}
	}
	
	static void buildMainPyramid(String input){
		String tempString = input;
		List<String> mainPyramid = new ArrayList<String>();
		int startIndex = 1;
		
		while(true){
			tempString = input.substring(input.length() - startIndex);
			input = input.substring(0, input.length() - startIndex);
			mainPyramid.add(tempString);
			startIndex +=2;
			if(startIndex >= input.length())
			{
				mainPyramid.add(input);
				break;
			}
		}
				
		buildTriangles(mainPyramid);		
	}
	
	static void buildTriangles(List<String> mainPyramid){
		int listIndex = 0;
		int arrayIndex = 0;
		int firstRawIndex = 0;
		int secondRawIndex = 0;
		boolean checkInvertedTriangle = false;
		
		Triangle[] triangles = new Triangle[countTriangles];
					
		for(int i = 0; i < triangles.length; i++){
			triangles[i] = new Triangle();
		}
		
		StringBuilder builder = new StringBuilder();
		StringBuilder builder1 = new StringBuilder();
		StringBuilder name = new StringBuilder();
		
		while(arrayIndex < triangles.length){
			Triangle triangle = triangles[arrayIndex];
			String triangleName = "";
			
			builder.append(mainPyramid.get(listIndex)).reverse();
			builder1.append(mainPyramid.get(listIndex + 1)).reverse();
									
			if(listIndex == 0){
				name.append(builder);
				name.append(builder1);
				triangleName = name.toString();
				triangle.setName(triangleName);
				triangles[arrayIndex] = triangle;
											
				builder.setLength(0);
				builder1.setLength(0);				
			} else if(!checkInvertedTriangle){
				if(name.length() == 0){
					name.append(builder.substring(firstRawIndex, firstRawIndex + 1));
					firstRawIndex ++;
				} else {
					name.append(builder1.substring(secondRawIndex, secondRawIndex + 3));
					secondRawIndex +=3;
				}
			} else {
				if(name.length() == 0){
					name.append(builder1.substring(secondRawIndex, secondRawIndex + 1));
					secondRawIndex++;
				} else {
					name.append(builder.substring(firstRawIndex, firstRawIndex + 3));
					firstRawIndex +=3;
				}
			}	 					
			
			if(name.length() == 4){
				name.reverse();
				triangleName = name.toString();
				triangle.setName(triangleName);
				triangles[arrayIndex] = triangle;
				arrayIndex++;
				name.setLength(0);
				if(checkInvertedTriangle){
					checkInvertedTriangle = false;
				} else {
					checkInvertedTriangle = true;
				}
			}
			
			if(firstRawIndex == builder.length()){
				firstRawIndex = 0;
				secondRawIndex = 0;
				listIndex +=2;
				builder.setLength(0);
				builder1.setLength(0);		
				checkInvertedTriangle = false;
			}		
			
		}		
		
		processTriangles(triangles);
				
		while(modifiedList.size() > 0){
			Triangle[] modifiedArray = new Triangle[modifiedList.size()];
			int i = 0;
			for(Triangle e: modifiedList){
				modifiedArray[i] = e;
				i++;
				System.out.println(e.getName());
			}
			processTriangles(modifiedArray);
		}
	}
	
	static void processTriangles(Triangle[] triangles){
		
		StringBuilder builder = new StringBuilder();
		modifiedList.clear();				
						
		for(int i = 0; i < triangles.length; i++){
			Triangle triangle = triangles[i];
			String name = triangle.getName();
			
			while(true){
				switch (name){
				case "0000" : name = "0000";
				break;
				case "0001" : name = "1000";
				break;
				case "0010" : name = "0001";
				break;
				case "0011" : name = "0010";
				break;
				case "0100" : name = "0000";
				break;
				case "0101" : name = "0010";
				break;
				case "0110" : name = "1011";
				break;
				case "0111" : name = "1011";
				break;
				case "1000" : name = "0100";
				break;
				case "1001" : name = "0101";
				break;
				case "1010" : name = "0111";
				break;
				case "1011" : name = "1111";
				break;
				case "1100" : name = "1101";
				break;
				case "1101" : name = "1110";
				break;
				case "1110" : name = "0111";
				break;		
				case "1111" : name = "1111";
				break;
				}
				if(name == "1111"){
					builder.append("1");
					break;
				}
				if(name == "0000"){
					builder.append("0");
					break;
				}
				
			}
			triangle.setName(name);
			triangles[i] = triangle;
		}
		
		for(int i = 0; i < triangles.length; i++){
			System.out.println(triangles[i].getName());
		}
		
		if(builder.length() == 1){
			System.out.println(builder);
		} else {
		
		StringBuilder temp = new StringBuilder();
		StringBuilder temp1 = new StringBuilder();
		
		for (int i = 0; i < builder.length(); i+=4){
			if(builder.length() == 4){
			temp.append(builder.substring(i, builder.length()));
			} else {
			temp.append(builder.substring(i, i + 4));	
			temp1.setLength(0);
			temp1.append(builder.substring(temp.length(), builder.length()));
			}
			temp.reverse();
			Triangle modifiedTriangle = new Triangle();
			modifiedTriangle.setName(temp.toString());
			modifiedList.add(modifiedTriangle);
			temp.setLength(0);
		}
		
		}
		
	}

}
