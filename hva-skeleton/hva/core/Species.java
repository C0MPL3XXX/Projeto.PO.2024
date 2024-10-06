package hva.core;

public class Species{
    private String uniqueId;
    

    public String getId(){
        return this.uniqueId;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Species){
            Species s = (Species)other;
            return this.uniqueId == s.getId();
        }
        return false; 
    }
}