package icc.test;

import java.util.Iterator;
import java.util.Random;
import icc.Lista;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link Lista}.
 */
public class TestLista {

    private Random random;
    private int total;
    private Lista<String> lista;

    /**
     * Crea un generador de números aleatorios para cada prueba, un número total
     * de elementos para nuestra lista, y una lista.
     */
    public TestLista() {
        random = new Random();
        total = 10 + random.nextInt(90);
        lista = new Lista<String>();
    }

    /**
     * Prueba unitaria para {@link Lista#getLongitud}.
     */
    @Test public void testGetLongitud() {
        for (int i = 0; i < total/2; i++) {
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
        for (int i = total/2; i < total; i++) {
            lista.agregaInicio(String.valueOf(random.nextInt(total)));
            Assert.assertTrue(lista.getLongitud() == i + 1);
        }
    }

    /**
     * Prueba unitaria para {@link Lista#agregaFinal}.
     */
    @Test public void testAgregaFinal() {
        lista.agregaFinal("1");
        lista.ultimo();
        Assert.assertTrue("1".equals(lista.get()));
        lista.agregaInicio("2");
        lista.ultimo();
        Assert.assertFalse("2".equals(lista.get()));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaFinal(e);
            lista.ultimo();
            Assert.assertTrue(e.equals(lista.get()));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#agregaInicio}.
     */
    @Test public void testAgregaInicio() {
        lista.agregaInicio("1");
        Assert.assertTrue("1".equals(lista.get()));
        lista.agregaFinal("2");
        Assert.assertFalse("2".equals(lista.get()));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaInicio(e);
            Assert.assertTrue(e == lista.get());
        }
    }

    /**
     * Prueba unitaria para {@link Lista#elimina}.
     */
    @Test public void testElimina() {
        int d = random.nextInt(total);
        String m = "";
        for (int i = 0; i < total; i++) {
            lista.agregaInicio(String.valueOf(d++));
            if (i == total / 2)
                m = String.valueOf(d - 1);
        }
        lista.primero();
        String p = (String)lista.get();
        lista.ultimo();
        String u = (String)lista.get();
        Assert.assertTrue(lista.contiene(p));
        Assert.assertTrue(lista.contiene(m));
        Assert.assertTrue(lista.contiene(u));
        lista.elimina(p);
        Assert.assertFalse(lista.contiene(p));
        lista.elimina(m);
        Assert.assertFalse(lista.contiene(m));
        lista.elimina(u);
        Assert.assertFalse(lista.contiene(u));
        total = lista.getLongitud();
        while (lista.getLongitud() > 0) {
            lista.primero();
            String n = (String)lista.get();
            lista.elimina(n);
            Assert.assertTrue(lista.getLongitud() == --total);
            if (lista.getLongitud() == 0)
                continue;
            n = (String)lista.get();
            lista.elimina(n);
            Assert.assertTrue(lista.getLongitud() == --total);
        }
    }

    /**
     * Prueba unitaria para {@link Lista#contiene}.
     */
    @Test public void testContiene() {
        int d = random.nextInt(total);
        String m = "";
        String n = String.valueOf(d - 1);
        for (int i = 0; i < total; i++) {
            lista.agregaFinal(String.valueOf(d++));
            if (i == total/2)
                m = String.valueOf(d - 1);
        }
        Assert.assertTrue(lista.contiene(m));
        Assert.assertTrue(lista.contiene(new String(m)));
        Assert.assertFalse(lista.contiene(n));
    }

    /**
     * Prueba unitaria para {@link Lista#reversa}.
     */
    @Test public void testReversa() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        Lista reversa = lista.reversa();
        Assert.assertTrue(reversa.getLongitud() == lista.getLongitud());
        lista.primero();
        reversa.ultimo();
        while (lista.iteradorValido() && reversa.iteradorValido()) {
            Assert.assertTrue(lista.get() == reversa.get());
            lista.siguiente();
            reversa.anterior();
        }
        Assert.assertFalse(lista.iteradorValido());
        Assert.assertFalse(reversa.iteradorValido());
    }

    /**
     * Prueba unitaria para {@link Lista#copia}.
     */
    @Test public void testCopia() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        Lista copia = lista.copia();
        Assert.assertFalse(lista == copia);
        lista.primero();
        copia.primero();
        while (lista.iteradorValido() && copia.iteradorValido()) {
            Assert.assertTrue(lista.get() == copia.get());
            lista.siguiente();
            copia.siguiente();
        }
        Assert.assertFalse(lista.iteradorValido());
        Assert.assertFalse(copia.iteradorValido());
    }

    /**
     * Prueba unitaria para {@link Lista#limpia}.
     */
    @Test public void testLimpia() {
        String primero = String.valueOf(random.nextInt(total));
        lista.agregaFinal(primero);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        String ultimo = String.valueOf(random.nextInt(total));
        lista.agregaFinal(ultimo);
        Assert.assertTrue(lista.getLongitud() != 0);
        lista.primero();
        Assert.assertTrue(lista.get().equals(primero));
        lista.ultimo();
        Assert.assertTrue(lista.get().equals(ultimo));
        lista.limpia();
        Assert.assertTrue(lista.getLongitud() == 0);
        Assert.assertTrue(lista.get() == null);
    }

    /**
     * Prueba unitaria para {@link Lista#get(int)}.
     */
    @Test public void testGet() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(random.nextInt(total));
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i++)
            Assert.assertTrue(lista.get(i) == a[i]);
        Assert.assertTrue(lista.get(-1) == null);
        Assert.assertTrue(lista.get(total) == null);
    }

    /**
     * Prueba unitaria para {@link Lista#indiceDe}.
     */
    @Test public void testIndiceDe() {
        int ini = random.nextInt(total);
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(ini + i);
            lista.agregaFinal(a[i]);
        }
        for (int i = 0; i < total; i ++)
            Assert.assertTrue(i == lista.indiceDe(a[i]));
        Assert.assertTrue(lista.indiceDe(String.valueOf(ini - 10)) == -1);
    }

    /**
     * Prueba unitaria para {@link Lista#toString}.
     */
    @Test public void testToString() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            a[i] = String.valueOf(i);
            lista.agregaFinal(a[i]);
        }
        String s = "[";
        for (int i = 0; i < total-1; i++)
            s += String.format("%s, ", a[i]);
        s += String.format("%s]", a[total-1]);
        Assert.assertTrue(s.equals(lista.toString()));
    }

    /**
     * Prueba unitaria para {@link Lista#primero}.
     */
    @Test public void testPrimero() {
        lista.agregaInicio("1");
        lista.ultimo();
        lista.primero();
        Assert.assertTrue(lista.get().equals("1"));
        lista.agregaFinal("2");
        lista.ultimo();
        lista.primero();
        Assert.assertFalse(lista.get().equals("2"));
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaInicio(e);
            lista.ultimo();
            lista.primero();
            Assert.assertTrue(lista.get().equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#ultimo}.
     */
    @Test public void testUltimo() {
        for (int i = 0; i < total; i++) {
            String u = String.valueOf(random.nextInt(total));
            lista.agregaFinal(u);
            lista.ultimo();
            Assert.assertTrue(lista.get().equals(u));
        }
    }

    /**
     * Prueba unitaria para {@link Lista#siguiente}.
     */
    @Test public void testSiguiente() {
        int ini = random.nextInt(total);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(ini + i));
        lista.primero();
        String n = (String)lista.get();
        lista.siguiente();
        Assert.assertFalse(lista.get().equals(n));
    }

    /**
     * Prueba unitaria para {@link Lista#anterior}.
     */
    @Test public void testAnterior() {
        int ini = random.nextInt(total);
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(ini + i));
        lista.ultimo();
        String n = (String)lista.get();
        lista.anterior();
        Assert.assertFalse(lista.get().equals(n));
    }

    /**
     * Prueba unitaria para {@link Lista#get}.
     */
    @Test public void testGetIterador() {
        String[] a = new String[total];
        for (int i = 0; i < total; i++) {
            String e = String.valueOf(random.nextInt(total));
            lista.agregaFinal(e);
            a[i] = e;
        }
        int i = 0;
        lista.primero();
        while (lista.iteradorValido()) {
            Assert.assertTrue(lista.get().equals(a[i++]));
            lista.siguiente();
        }
        lista.limpia();
    }

    /**
     * Prueba unitaria para {@link Lista#iteradorValido}.
     */
    @Test public void testIteradorValido() {
        for (int i = 0; i < total; i++)
            lista.agregaFinal(String.valueOf(random.nextInt(total)));
        lista.primero();
        Assert.assertTrue(lista.iteradorValido());
        lista.ultimo();
        Assert.assertTrue(lista.iteradorValido());
        lista.primero();
        lista.anterior();
        Assert.assertFalse(lista.iteradorValido());
        lista.ultimo();
        lista.siguiente();
        Assert.assertFalse(lista.iteradorValido());
    }

    /**
     * Prueba unitaria para {@link Lista#equals}.
     */
    @Test public void testEquals() {
        Lista<String> otra = new Lista<String>();
        for (int i = 0; i < total; i++) {
            String r = String.valueOf(random.nextInt(total));
            lista.agregaFinal(r);
            otra.agregaFinal(new String(r));
        }
        Assert.assertTrue(lista.equals(otra));
        lista.ultimo();
        String u = (String)lista.get();
        lista.elimina(u);
        lista.agregaFinal(u + "1");
        Assert.assertFalse(lista.equals(otra));
    }
}
