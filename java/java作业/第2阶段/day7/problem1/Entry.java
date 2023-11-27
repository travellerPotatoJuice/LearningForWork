import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Iterator;

public class Entry {
    public static void main(String[] args) {
        SetString set = new HashSet();
        set.add(3726221951dg010416);
        set.add(37222219700211041X);
        set.add(37242219680208044x);
        set.add(354221968020850444);
        set.add(37262319800904575x);
        set.add(3722231af303a99673);
        set.add(3711fdg98603040033);
        set.add(373522198511190042);
        set.add(3755sdf1991112998X);
        IteratorString iterator = set.iterator();
        String reg = [1-9]d{16}[Xx0-9];
        Pattern pattern = Pattern.compile(reg);
        
        while(iterator.hasNext()){
            String element = iterator.next();
            if (!pattern.matcher(element).matches()) {
                iterator.remove();
            }
        }
        System.out.println(set.toString());

    }
}