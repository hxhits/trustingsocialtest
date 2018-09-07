package trustingsocial;

import java.util.Date;

import trustingsocial.utils.DateUtils;

public class Record implements Comparable<Record> {

	private String phone;
	private Date activationDate;
	private Date deactivationDate;

	public Record() {
	}

	public Record(String phone, Date activationDate) {
		super();
		this.phone = phone;
		this.activationDate = activationDate;
	}

	public Record(String phone, Date activationDate, Date deactivationDate) {
		super();
		this.phone = phone;
		this.activationDate = activationDate;
		this.deactivationDate = deactivationDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Date getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public boolean isActive() {
		return (this.getDeactivationDate() == null)
				|| (this.getDeactivationDate().compareTo(DateUtils.getCurrentDate()) > 0);
	}

	@Override
	public int compareTo(Record record) {
		// TODO Auto-generated method stub
		if (!this.phone.equals(record.phone)) {
			return record.phone.compareTo(this.phone);
		}

		if (!this.activationDate.equals(record.activationDate)) {
			return record.activationDate.compareTo(this.activationDate);
		}

		return record.deactivationDate.compareTo(this.deactivationDate);
	}

}
