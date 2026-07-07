CREATE TABLE `big_data_temp`
(
    `id`                    varchar(255) NOT NULL,
    `resource_browsing_num` int(11)      NOT NULL,
    `resource_download_num` int(11)      NOT NULL,
    `last_completion_rate`  double       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `big_data_temp`(`id`, `resource_browsing_num`, `resource_download_num`, `last_completion_rate`)
VALUES ('1', 100, 1000, 55);
