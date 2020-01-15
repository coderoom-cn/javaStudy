
#多线程Future设计模式

Future模式的主要角色有：
FutureMain：系统启动，调用FutureClient发出请求
FutureClient：返回Data对象，立即返回FutureData(VirtualData)，并开启线程去获取RealData
Data：返回数据的接口
FutureData：虚拟数据，返回很快，需要装载RealData
RealData：真实数据

[========]

### 时序图

#### 传统时序图
```seq
客户端Future->FutureData包装类: call()
FutureData包装类->RealData真实数据类: other call()
FutureData包装类-->客户端Future: call() return
RealData真实数据类-->客户端Future: call() return
客户端Future->客户端Future: other_job
客户端Future->客户端Future: other_job
```
#### Future设计模式序列图 Sequence Diagram

```seq
客户端Future->FutureData包装类: call()
客户端Future->客户端Future: other_job
客户端Future->客户端Future: other_job
FutureData包装类->RealData真实数据类: other call()
FutureData包装类-->客户端Future: call() return
RealData真实数据类-->客户端Future: call() return
```

客户端调用购物请求，服务端程序不等数据处理完成便立即返回客户端一个伪造的数据，
（相当于订单，而不是真实的商品）这时候由服务端自己偷偷的发送了一个other call()请求去获取真实的商品(打包，出库，送货)。
这就是Future模式的核心所在。

