CREATE TABLE IF NOT EXISTS `pizza` (
    `id` varchar(64) NOT NULL,
    `name` varchar(100) NOT NULL,
    `price` decimal(11, 2) NOT NULL,
    `creation_time` datetime NOT NULL,
    `modification_time` datetime NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_name` (`name`)
);
