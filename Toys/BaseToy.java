package Toys;

public abstract class BaseToy implements BaseInterface {
    protected int id;
    protected String name;
    protected int count;
    protected int chance;
    public int ch1;
    public int ch2;

    public BaseToy(int id, String name, int count, int chance){
        this.id=id;
        this.name=name;
        this.count=count;
        this.chance=chance;
        this.ch1=0;
        this.ch2=0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getChance() {
        return chance;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void setChance(int chance) {
        this.chance = chance;
    }
}
