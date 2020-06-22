### Tasking--Homework1

- Given 有空储物柜 when存包 then 存包成功 返回一张票
- Given 无储物柜 when存包 then 存包失败 提示储物柜已满
- Given 票有效 when取包 then 取包成功
- Given 伪造票 when取包 then 取包失败，提示该票为伪造，无效
- Given 重复使用过的票 when取包 then 取包失败，提示该票已使用，无效

### Tasking--Homework2

- Given 两个柜子被机器人管理，且都不满 when 让机器人存包 then 存包成功，返回票据
- Given 两个柜子被机器人管理，且只有第二个不满 when 让机器人存包 then 存包成功，返回票据
- Given 两个柜子被机器人管理，且都满了 when 让机器人存包 then 存包失败，提示储物柜都已满
- Given 两个柜子被机器人管理，when 给机器人有效票 then 取包成功
- Given 两个柜子被机器人管理, when 给机器人伪造票 then 取包失败，提示票为伪造，无效
- Given 两个柜子被机器人管理, when 给机器人已使用的票 then 取包失败，提示票已使用，无效

### Tasking--Homework3

- Given：SmartLockerRobot 管理Locker1，Locker2，Locker1 剩余容量  > Locker2 剩余容量
  When：SmartLockerRobot存包
  Then：返回ticket，成功存入Locker1

- Given：SmartLockerRobot 管理Locker1，Locker2，Locker1 剩余容量  < Locker2 剩余容量
  When：SmartLockerRobot存包
  Then：返回ticket，成功存入Locker2

- Given：SmartLockerRobot 管理Locker1，Locker2，Locker1 剩余容量  = Locker2 剩余容量
  When：SmartLockerRobot存包
  Then：返回ticket，成功存入Locker1

- Given：SmartLockerRobot 管理Locker1，Locker2，Locker1 ，Locker2 剩余容量 为 0
  When：SmartLockerRobot存包
  Then：存包失败，提示locker已满，

- Given：SmartLockerRobot 管理Locker1，Locker2，有一张有效ticket
  When：SmartLockerRobot取包
  Then：成功取出原有的包，

- Given：SmartLockerRobot 管理Locker1，Locker2，有一张无效ticket
  When：SmartLockerRobot取包
  Then：取包失败，无效票

- Given：SmartLockerRobot  PrimitiveLockerRobot 同时 管理Locker1，Locker2 ,有效票
  When：SmartLockerRobot存包 ，PrimitiveLockerRobot 取包
  Then： 成功取出原有的包，

- Given：SmartLockerRobot  PrimitiveLockerRobot 同时 管理Locker1，Locker2 ,有效票
  When：PrimitiveLockerRobot存包 ，SmartLockerRobot 取包
  Then： 成功取出原有的包

### Tasking--Homework4

- Given: LockerRobotManager管理一个Locker，且按顺序管理1个PrimaryLockerRobot（有一个Locker，存满）,1个SmartLockerRobot（有两个Locker，其中第一个
存满，第二个未存满）
  When: LockerRobotManager存包
  Then: 返回ticket，成功存入SmartLockerRobot的第二个Locker

- Given: LockerRobotManager管理一个Locker，且按顺序管理1个PrimaryLockerRobot（有一个Locker，存满）,1个SmartLockerRobot（有两个Locker，其中第一个
未存满，第二个存满）
  When: LockerRobotManager存包
  Then: 返回ticket，成功存入SmartLockerRobot的第一个Locker  