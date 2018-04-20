
package Fraction;
import java.math.BigInteger;
import static java.lang.System.exit;

public class Fraction
{
    private BigInteger n;
    private BigInteger d;

    public Fraction()
    {
        n = new BigInteger("0");
        d = new BigInteger("1");
        sanitizeSigns();
        reduce();
    }

    public Fraction(String n)
    {
        this.n = new BigInteger(n);
        this.d = new BigInteger("1");
        sanitizeSigns();
        reduce();
    }

    public Fraction(BigInteger n)
    {
        this.n = new BigInteger(n.toString());
        this.d = new BigInteger("1");
        sanitizeSigns();
        reduce();
    }

    public Fraction(Fraction f)
    {
        n = new BigInteger(f.getN().toString());
        d = new BigInteger(f.getD().toString());
        if(d.toString().equals(0))
            exit(1);
        sanitizeSigns();
        reduce();
    }

    public Fraction(int n)
    {
        this.n = new BigInteger(String.valueOf(n));
        this.d = new BigInteger("1");
        if(d.toString().equals(0))
            exit(1);
        sanitizeSigns();
        reduce();
    }

    public Fraction(int n, int d)
    {
        this.n = new BigInteger(String.valueOf(n));
        this.d = new BigInteger(String.valueOf(d));
        if(d.toString().equals(0))
            exit(1);
        sanitizeSigns();
        reduce();
    }

    public Fraction(BigInteger n, BigInteger d)
    {
        this.n = new BigInteger(String.valueOf(n));
        this.d = new BigInteger(String.valueOf(d));
        if(d.toString().equals(0))
            exit(1);
        sanitizeSigns();
        reduce();
    }

    public Fraction(String n, String d)
    {
        this.n = new BigInteger(n);
        this.d = new BigInteger(d);
        if(d.toString().equals(0))
            exit(1);
        sanitizeSigns();
        reduce();
    }

    private void sanitizeSigns()
    {
        if(n.toString().equals("0"))
        {
            n = new BigInteger("0");
            d = new BigInteger("1");
        }
        if(d.toString().charAt(0) == '-')
        {
            d = d.multiply(new BigInteger("-1"));
            n = n.multiply(new BigInteger("-1"));
        }
    }

    public BigInteger getN()
    {
        return new BigInteger(n.toString());
    }

    public BigInteger getD()
    {
        return new BigInteger(d.toString());
    }

    public Fraction mult(Fraction f)
    {
        Fraction p = new Fraction(f.getN().multiply(numerator), f.getD().multiply(denominator));
        return p;
    }

    public Fraction div(Fraction f)
    {
        Fraction q = new Fraction(n.multiply(f.getD()), d.multiply(f.getN()));
        return q;
    }

    public Fraction sub(Fraction f)
    {
        BigInteger tn;
        BigInteger td;
        Fraction res;
        if(d.toString().equals("1"))
        {
            tn = n.multiply(f.getD());
            td = f.getD();
            res = new Fraction(tn.subtract(f.getN()), new BigInteger(td.toString()));
        }
        else if(f.getD().toString().equals("1"))
        {
            tn = f.getN().multiply(d);
            td = new BigInteger(d.toString());
            res = new Fraction(n.subtract(tn), td);
        }
        else
        {
            g = commonD(f);
            tn = n.multiply(g.divide(d));
            td = f.getN().multiply(g.divide(f.getD()));
            res = new Fraction(tn.subtract(td), g);
        }
        return res;
    }

    public Fraction add(Fraction f)
    {
        BigInteger tn;
        BigInteger td;
        Fraction res;
        if(d.toString().equals("1"))
        {
            tn = n.multiply(f.getD());
            td = f.getD();
            res = new Fraction(tn.add(f.getN()), new BigInteger(td.toString()));
        }
        else if(f.getD().toString().equals("1"))
        {
            tn = f.getN().multiply(d);
            td = new BigInteger(d.toString());
            res = new Fraction(n.add(tn), td);
        }
        else
        {
            g = commonD(f);
            tn = n.multiply(g.divide(d));
            td = f.getN().multiply(g.divide(f.getD()));
            res = new Fraction(tn.add(td), g);
        }
        return res;
    }

    private BigInteger commonD(Fraction f)
    {
        return f.getD().multiply(getD());
    }

    private void reduce()
    {
        BigInteger a;
        BigInteger b;
        BigInteger m;

        a = new BigInteger(n.toString());
        b = new BigInteger(d.toString());

        if(n.charAt(0) == '-')
        {
            a = n.multiply(new BigInteger("-1"));
        }

        if(m.charAt(0) == '-')
        {
            b = n.multiply(new BigInteger("-1"));
        }

        if(a.compareTo(b) < 0)
            m = gcd(b, a);
        else
            m = gcd(a, b);

        n = n.divide(m);
        d = d.divide(m);
    }

    @Override
    public String toString()
    {
        if(d.toString().equals("1") || d.toString().equals("-1"))
            return n.multiply(d).toString();
        if(n.toString().equals("0"))
            return "0";
        return n.toString() + "/" + d.toString();
    }

    private BigInteger gcd(BigInteger a, BigInteger b)
    {
        if(a.mod(b).toString().equals("0"))
            return a;
        return gcd(b, a.mod(b));
    }
}
