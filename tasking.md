Tasking--Homework1

- Given 有空储物柜 when存包 then 存包成功 返回一张票
- Given 无储物柜 when存包 then 存包失败 提示储物柜已满
- Given 票有效 when取包 then 取包成功
- Given 伪造票 when取包 then 取包失败，提示该票为伪造，无效
- Given 重复使用过的票 when取包 then 取包失败，提示该票已使用，无效

Tasking--Homework2

- Given 两个柜子被机器人管理，且都不满 when 让机器人存包 then 存包成功，返回票据
- Given 两个柜子被机器人管理，且只有第二个不满 when 让机器人存包 then 存包成功，返回票据
- Given 两个柜子被机器人管理，且都满了 when 让机器人存包 then 存包失败，提示储物柜都已满
- Given 两个柜子被机器人管理，when 给机器人有效票 then 取包成功
- Given 两个柜子被机器人管理, when 给机器人伪造票 then 取包失败，提示票为伪造，无效
- Given 两个柜子被机器人管理, when 给机器人已使用的票 then 取包失败，提示票已使用，无效
