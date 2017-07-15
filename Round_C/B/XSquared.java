import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class XSquared {

	private static boolean isSingleMeet = false;
	public static boolean check(int x, int y, int N, char[][] inputs) {
		int row = 0, col = 0;
		int rowIdx = 0, colIdx = 0;
		for (int i = 0; i < N; i++) {
			if (inputs[x][i] == 'X') {
				row++;
				if (i != y) colIdx = i;
			}
			
			if (inputs[i][y] == 'X') {
				col++;
				if (i != x) rowIdx = i;
			}
		}
		
		if (row == 1 && col == 1) {
			if (!isSingleMeet) {
				isSingleMeet = true;
				return true;
			}
			return false;
		}
		if (row != 2 || col != 2) return false;
		return inputs[rowIdx][colIdx] == 'X';
	}
	
	public static void main(String[] args) {
		try {
			File in = new File("B-large-practice.in");
			File out = new File("B-large-practice.out");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(in));
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(out));
			
			BufferedReader bf = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(osw);
			
			String line = null;
			
			line = bf.readLine();
			int nums = Integer.parseInt(line);
			
			for (int i = 0; i < nums; i++) {
				int N = Integer.parseInt(bf.readLine());
				char[][] inputs = new char[N][N];
				for (int j = 0; j < N; j++) {
					line = bf.readLine();
					inputs[j] = line.toCharArray();
				}
				
				bw.write("Case #" + (i+1) + ": ");
				
				isSingleMeet = false;
				int sum = 0;
				for (int row = 0; row < N; row++) {
					for (int col = 0; col < N; col++)
						if (inputs[row][col] == 'X')
							sum += 1;
				}
				
				if (sum != N * 2 - 1) {
					bw.write("IMPOSSIBLE" + '\n');
					continue;
				}
				
				boolean temp = true;
				for (int row = 0; temp && row < N; row++) {
					for (int col = 0; temp && col < N; col++) {
						if (inputs[row][col] == 'X') {
							temp = check(row, col, N, inputs);
						}
					}
				}
				if (temp) {
					bw.write("POSSIBLE" + '\n');
				} else 
					bw.write("IMPOSSIBLE" + '\n');
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