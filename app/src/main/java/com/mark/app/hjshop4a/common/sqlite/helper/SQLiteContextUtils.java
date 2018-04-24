package com.mark.app.hjshop4a.common.sqlite.helper;

/**
 * sqlite数据表结构
 * Created by lenovo on 2017/10/4.
 */

public interface SQLiteContextUtils {
    /**
     * 数据库名称
     */
    String DB_NAME = "hjshop.db";
    /**
     * 版本号
     */
    int DB_VERSION = 1;

    /**
     * 数据库表
     */
    interface TABLE {

        /**
         * 购物车表
         */
        interface SHOPCAR {
            /**
             * 表名称
             */
            String NAME = "loginrepo";

            /**
             * 数据库表字段
             */
            interface FIELD {
                /**
                 * 购物车主键
                 * 是否必须：true
                 */
                String ID = "shopcar_id";
                /**
                 * 店铺id
                 */
                String SHOP_ID = "shop_id";
                /**
                 * 商品id
                 * 是否必须：true
                 */
                String PRODUCT_ID = "product_id";
                /**
                 * 商品skuId
                 * 是否必须：false
                 */
                String PRODUCT_SKU_ID = "product_sku_id";
                /**
                 * 商品skuId
                 * 是否必须：false
                 */
                String PRODUCT_SKU_NAME = "product_sku_name";
                /**
                 * 商品名称
                 * 是否必须：true
                 */
                String PRODUCT_NAME = "product_name";
                /**
                 * 商品单价
                 * 是否必须：true
                 */
                String PRODUCT_PRICE = "product_price";
                /**
                 * 商品数量
                 * 是否必须：true
                 */
                String PRODUCT_COUNT = "product_count";
                /**
                 * 商品库存
                 */
                String PRODUCT_STORE = "product_store";
                /*
                * 商品tag*/
                String PRODUCT_TAGID="product_tagId";
            }

            /**
             * sql语句
             */
            interface SQL {
                /**
                 * 创建表
                 */
                String CREATE = "CREATE TABLE `" + SHOPCAR.NAME + "` (\n" +
                        "`" + FIELD.ID + "`  INTEGER NOT NULL  PRIMARY KEY ,\n" +
                        "`" + FIELD.SHOP_ID + "`  bigint(20) NOT NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.PRODUCT_ID + "`  bigint(20) NOT NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.PRODUCT_SKU_ID + "`  bigint(20) NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.PRODUCT_SKU_NAME + "`  varchar(255) NULL DEFAULT '' ,\n" +
                        "`" + FIELD.PRODUCT_NAME + "`  varchar(255) NOT NULL DEFAULT '' ,\n" +
                        "`" + FIELD.PRODUCT_PRICE + "`  varchar(255) NOT NULL DEFAULT '0' ,\n" +
                        "`" + FIELD.PRODUCT_COUNT + "`  int(11) NOT NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.PRODUCT_STORE + "`  int(11) NOT NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.PRODUCT_TAGID + "`  varchar(255) NOT NULL DEFAULT '' \n" +
                        ")";
                /**
                 * 删除
                 */
                String DELETE = "DROP TABLE IF EXISTS " + SHOPCAR.NAME;
            }
        }

        /**
         * 公共数据表
         */
        interface COMMON {
            /**
             * 表名称
             */
            String NAME = "common";

            /**
             * 数据库表字段
             */
            interface FIELD {
                /**
                 * id
                 */
                String ID = "id";
                /**
                 * 用户id
                 */
                String USER_ID = "user_id";
                /**
                 * json数据
                 */
                String JSON = "json";
                /**
                 * json数据类型
                 */
                String TYPE = "type";
            }

            /**
             * type的值
             */
            interface VALUE {
                /**
                 * 信用卡
                 */
                int CARD = 1;
            }

            /**
             * sql语句
             */
            interface SQL {
                /**
                 * 创建表
                 */
                String CREATE = "CREATE TABLE `" + COMMON.NAME + "` (\n" +
                        "`" + FIELD.ID + "`  INTEGER NOT NULL  PRIMARY KEY ,\n" +
                        "`" + FIELD.USER_ID + "`  bigint(20) NOT NULL DEFAULT 0 ,\n" +
                        "`" + FIELD.JSON + "`  varchar(255) NOT NULL DEFAULT '' ,\n" +
                        "`" + FIELD.TYPE + "`  int(11) NOT NULL DEFAULT 0 \n" +
                        ")";
                /**
                 * 删除
                 */
                String DELETE = "DROP TABLE IF EXISTS " + COMMON.NAME;
            }
        }
        /*
        * 国家
        * */
        interface  COUNTRY{
            /**
             * 表名称
             */
            String NAME = "country";
            /**
             * 数据库表字段
             */
            interface FIELD{
                /**
                 * country
                 */
                String COUNTRY = "country";
            }
            /**
             * sql语句
             */
            interface SQL{
                /**
                 * 创建表
                 */
                String CREATE ="CREATE TABLE `" + COUNTRY.NAME +  "` (\n" +
                        "`" + FIELD.COUNTRY + "`  varchar(255) NOT NULL DEFAULT '' \n" +
                        ")";
                /**
                 * 删除
                 */
                String DELETE = "DROP TABLE IF EXISTS " + COUNTRY.NAME;
            }
        }
    }
}
