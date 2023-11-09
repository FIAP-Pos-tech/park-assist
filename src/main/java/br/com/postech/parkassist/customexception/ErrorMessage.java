package br.com.postech.parkassist.customexception;



public class ErrorMessage {
	private String title;
	private String message;
    private int status;
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ErrorMessage(String title, String message, int status ) {
        super();
        this.title = title;
        this.message = message;
        this.status = status;
    }

    public ErrorMessage() {
        super();
    }
}
