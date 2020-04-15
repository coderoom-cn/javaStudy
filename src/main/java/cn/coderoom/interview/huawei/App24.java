package cn.coderoom.interview.huawei;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * 数据分类处理）（Java）
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App24
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:16
 */
public class App24 {

    public static void main ( String args[] ) {
        Scanner in = new Scanner( System.in );
        while( in.hasNext() ) {
            String s = "";
            int numI = in.nextInt();
            int I[] = new int[ numI ];
            for ( int i = 0 ; i < numI ; i++ ) {
                I[ i ] = in.nextInt();
            }
            in.nextLine();

            int numR = in.nextInt();
            int R[] = new int[ numR ];
            for ( int i = 0 ; i < numR ; i++ ) {
                R[ i ] = in.nextInt();
            }

            TreeSet<Integer> treeR = new TreeSet<Integer>();
            for ( int i = 0 ; i < numR ; i++ ) {
                treeR.add( R[ i ] );
            }

            Iterator<Integer> it = treeR.iterator();

            int number[] = new int[ treeR.size() ];
            for ( int i = 0 ; i < treeR.size() ; i++ ) {
                int n = it.next();
                String s1 = "";

                for ( int j = 0 ; j < numI ; j++ ) {

                    if ( match( I[ j ] , n )) {
                        number[ i ]++;
                        s1 = s1 + j + " ";
                        s1 = s1 + I[ j ] + " ";
                    }
                }
                if ( number[ i ] != 0 ) {
                    s = s + n + " " + number[ i ] + " "+ s1;
                }
            }

            String[] a = s.split(" ");

            s = a.length + " " + s;
            System.out.println( s );
        }
    }

    static boolean match( int a1 , int a2 ) {
        String s1 = String.valueOf( a1 );
        String s2 = String.valueOf( a2 );
        if ( s1.indexOf( s2 ) == -1 ) {
            return false;
        }
        else {
            return true;
        }
    }

}
