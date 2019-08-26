package dto;

public class Model {
	
	private String modelName;
	private int tank;
	private double dayPrice;
	
	
	
	
	
	public Model() {
		super();
	}
	public Model(String modelName, int tank, double dayPrice) {
		super();
		this.modelName = modelName;
		this.tank = tank;
		this.dayPrice = dayPrice;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getTank() {
		return tank;
	}
	public void setTank(int tank) {
		this.tank = tank;
	}
	public double getDayPrice() {
		return dayPrice;
	}
	public void setDayPrice(double dayPrice) {
		this.dayPrice = dayPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dayPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + tank;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (Double.doubleToLongBits(dayPrice) != Double.doubleToLongBits(other.dayPrice))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (tank != other.tank)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Model [modelName=" + modelName + ", tank=" + tank + ", dayPrice=" + dayPrice + "]";
	}

	
	
}
