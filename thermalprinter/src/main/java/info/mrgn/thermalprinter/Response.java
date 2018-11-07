package info.mrgn.thermalprinter;

public interface Response {
    public void success(int status_code, String message);
    public void failed(int status_code, String message);
}
