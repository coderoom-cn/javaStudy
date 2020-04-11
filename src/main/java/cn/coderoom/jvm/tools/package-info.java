/**
 * 怎样选择工具
 * java自带的一些工具，各有利弊，其实如果在开发环境，使用什么样的工具是无所谓的，只要能得到结果就好。
 * 但是在生产环境里，却不能乱选择，因为这些工具本身就会耗费大量的系统资源，如果在一个生产服务器压力很大的时候，贸然执行这些工具，可能会造成很意外的情况。
 * 最好不要在服务器本机监控，远程监控会比较好一些，但是如果要远程监控，服务器端的启动脚本要加入一些jvm参数，
 * 例如用jconsloe远程监控tomcat或jboss等，都需要设置jvm的jmx参数，如果仅仅只是分析服务器的内存分配和gc信息，
 * 强烈推荐，先用jmap导出服务器端的jvm的堆dump文件，然后再用jhat，或者jvisualvm，或者eclipse内存分析器来分析内存状况。
 *
 * ##性能优化##的关键并不在于怎么进行优化，而在于怎么找到当前系统的性能瓶颈。
 * @class
 * @package cn.coderoom.jvm.tools
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/8 14:00
*/
package cn.coderoom.jvm.tools;