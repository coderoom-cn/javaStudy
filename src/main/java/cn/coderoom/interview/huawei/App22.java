package cn.coderoom.interview.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * 查找兄弟单词）（Java）
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App22
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:14
 */
public class App22 {

    public static void main ( String[] args ) throws IOException {
        BufferedReader bf = new BufferedReader( new InputStreamReader( System.in) );
        String s;
        while( ( s = bf.readLine() ) != null ) {
            String str[] = s.split("\\s+");
            String brother[] = new String[ str.length ];

            int n = Integer.parseInt(str[ 0 ]);
            String words = str[ n + 1 ];
            int k = Integer.parseInt( str[ str.length - 1 ] );

            int j = 0;
            for( int i = 1 ; i <= n ; i++ ) {
                if ( match( words , str[ i ] ) ) {
                    brother[ j ] = str[ i ];
                    j++;
                }
            }

            String a[] = new String[ j ];
            for ( int i = 0 ; i < j ; i++ ) {
                a[ i ] = brother[ i ];
            }


            if ( j == 0 ) {
                System.out.println( 0 );
            }
            else if ( k - 1 > j ) {
                System.out.println( j );
            }
            else {
                System.out.println( j );
                Arrays.sort( a );
                System.out.println( a[ k - 1 ] );
            }
        }
    }

    static boolean match( String s1 , String s2 ) {

        int a1[] = new int[ 26 ];
        int a2[] = new int[ 26 ];

        if ( !s1.equals( s2 ) ) {
            for ( int i = 0 ; i < 26 ; i++ ) {
                char c = (char) (i + 'a');
                for ( int j = 0 ; j < s1.length(); j++ ) {
                    if ( c == s1.charAt( j ) ) {
                        a1[ i ]++;
                    }
                }
                for ( int k = 0 ; k < s2.length(); k++ ) {
                    if ( c == s2.charAt( k ) ) {
                        a2[ i ]++;
                    }
                }
            }
            int judgement = 0;
            for ( int i = 0 ; i < 26 ; i++ ) {
                if ( a1[ i ] != a2[ i ] ) {
                    judgement++;
                }
            }

            if ( judgement == 0 ) {
                return true;
            }
            else {
                return false;
            }
        }

        else {
            return false;
        }
    }

}
