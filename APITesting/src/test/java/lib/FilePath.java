package lib;

public interface FilePath {
	String FILE_PATH="D:\\APITestingWorkspace\\practice\\APITesting\\SHEET1.xlsx";
	Xls_AllMethods excel = new Xls_AllMethods(FILE_PATH);
	String API_SHEETNAME = "APITest";
}
