package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="orders")
public class Orders implements Serializable
{

	private int id;
	private int userid;
	private String name;
	private double price;
	private double total_price;
	private Date create_time;
	private Date modifiy_time;
	private int number;

	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModifiy_time() {
        return modifiy_time;
    }

    public void setModifiy_time(Date modifiy_time) {
        this.modifiy_time = modifiy_time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
