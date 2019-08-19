ALTER TABLE `campgem`.`member`
	ADD COLUMN `shipping_methods` json NULL COMMENT '配送方式，e:xxx}, {name:yyy,price:yyy}]' AFTER `extend_3`;[{name:xxx,pric