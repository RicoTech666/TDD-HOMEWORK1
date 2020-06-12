Tasking

- Given有空储物柜 when存包 then 存包成功 返回一张票
- Given无储物柜 when存包 then 存包失败 提示储物柜已满
- Given票有效 when取包 then 取包成功
- Given伪造票 when取包 then取包失败，提示该票为伪造，无效
- Given重复使用过的票 when取包 then取包失败，提示该票已使用，无效
