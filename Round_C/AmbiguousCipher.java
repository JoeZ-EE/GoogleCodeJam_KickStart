import java.io.*;

public class AmbiguousCipher {

	public static String decrypt(String str) {
		char[] b = str.toCharArray();
		int n = b.length;
		char[] a = new char[n];
		a[1] = b[0];
		for (int i = 2; i < n; i += 2) {
			a[i + 1] = (char)((b[i] - a[i - 1] + 26) % 26 + 'A');
		}
		a[n - 2] = b[n - 1];
		for (int i = n - 3; i >= 0; i -= 2) {
			a[i - 1] = (char)((b[i] - a[i + 1] + 26) % 26 + 'A');
		}
		return new String(a);
	}
	
	public static void main(String[] args) {
		try {
			File in = new File("in.in");
			File out = new File("out.out");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(in));
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(out));
			
			BufferedReader bf = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(osw);
			
			String line = null;
			
			line = bf.readLine();
			int num = Integer.parseInt(line);
			for (int i = 0; i < num; i++) {
				line = bf.readLine();
				int len = line.length();
				osw.write("Case #" + (i+1) + ": ");
				if ((len & 1) == 1)
					osw.write("AMBIGUOUS" + '\n');
				else 
					osw.write(decrypt(line) + '\n');
			}
			
			bw.close();
			bf.close();
			osw.close();
			isr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
