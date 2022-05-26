package HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);


		String input= "";
		Class theClass=RichSoilLand.class;
		Field[] fields = theClass.getDeclaredFields();


		for (int i = 0; i < 100; i++) {
			input= sc.nextLine();
			if (input.equals("HARVEST")){
				break;
			}
			showFields(input ,fields);

		}

	}

	private static void showFields(String input,Field[] fields) {
		for (Field el:fields) {
			int modifier=el.getModifiers();
			switch (input) {
				case "protected":
					if (Modifier.isProtected(modifier)) {
						System.out.printf("%s %s %s%n",input, el.getType().getSimpleName(), el.getName());
					}
					break;
				case "public":
					if (Modifier.isPublic(modifier)) {
						System.out.printf("%s %s %s%n",input, el.getType().getSimpleName(), el.getName());
					}
					break;
				case "private":
					if (Modifier.isPrivate(modifier)) {
						System.out.printf("%s %s %s%n",input, el.getType().getSimpleName(), el.getName());
					}
					break;
				case "all":
						System.out.printf("%s %s %s%n",Modifier.toString(el.getModifiers()), el.getType().getSimpleName(), el.getName());
					break;
				default:
					throw new IllegalArgumentException("Wrong Field modifier!");

			}




		}


	}
}
