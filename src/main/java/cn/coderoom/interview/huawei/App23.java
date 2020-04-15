package cn.coderoom.interview.huawei;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 字符串加解密）（Java）
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App23
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:15
 */
public class App23 {

    public static void main ( String[] args ) throws IOException {
        BufferedReader bf = new BufferedReader( new InputStreamReader( System.in ) );
        String s ;
        while( ( s = bf.readLine() ) != null ) {

            char aucPassword[] = s.toCharArray();
            char result[] = bf.readLine().toCharArray();

            int length1;
            if ( aucPassword.length <= 100 ) {
                length1 = aucPassword.length;
            }
            else {
                length1 = 100;
            }
            char aucResult[] = new char[ length1 ];

            int length2;
            if ( result.length <= 100 ) {
                length2 = result.length;
            }
            else {
                length2 = 100;
            }
            char password[] = new char[ length2 ];

            Encrypt ( aucPassword , aucResult );
            unEncrypt ( result , password );
        }
        bf.close();
    }

    static void Encrypt (char aucPassword[], char aucResult[]) {

        for ( int i = 0 ; i < aucResult.length ; i++ ) {
            if ( aucPassword[ i ] >= 65 && aucPassword[ i ] <= 90 ) {
                if ( aucPassword[ i ] == 'Z' ) {
                    aucResult[ i ] = 'a';
                }
                else {
                    aucResult[ i ] = (char) (aucPassword[ i ] + 33 );
                }
            }

            else  if ( aucPassword[ i ] >= 97 && aucPassword[ i ] <= 122 ) {
                if ( aucPassword[ i ] == 'z' ) {
                    aucResult[ i ] = 'A';
                }
                else {
                    aucResult[ i ] = (char) (aucPassword[ i ] - 31 );
                }
            }

            else if ( aucPassword[ i ] >= 48 && aucPassword[ i ] <= 57 ) {
                if ( aucPassword[ i ] == '9' ) {
                    aucResult[ i ] = '0';
                }
                else {
                    aucResult[ i ] = (char) (aucPassword[ i ] + 1 );
                }
            }

            else {
                aucResult[ i ] = aucPassword[ i ];
            }
        }

        System.out.println( String.valueOf( aucResult ) );
    }

    static int unEncrypt (char result[], char password[]) {
        for ( int i = 0 ; i < password.length ; i++ ) {
            if ( result[ i ] >= 65 && result[ i ] <= 90 ) {
                if ( result[ i ] == 'A' ) {
                    password[ i ] = 'z';
                }
                else {
                    password[ i ] = (char) ( result[ i ] + 31 );
                }
            }
            else if ( result[ i ] >= 97 && result[ i ] <= 122 ) {
                if ( result[ i ] == 'a' ) {
                    password[ i ] = 'Z';
                }
                else {
                    password[ i ] = ( char )( result[ i ] - 33 );
                }
            }
            else if ( result[ i ] >= 48 && result[ i ] <= 57 ) {
                if ( result[ i ] == '0' ) {
                    password[ i ] = '9';
                }
                else {
                    password[ i ] = ( char )( result[ i ] - 1 );
                }
            }
            else {
                password[ i ] = result[ i ];
            }
        }
        System.out.println( String.valueOf( password ) );
        return 0;
    }

}
