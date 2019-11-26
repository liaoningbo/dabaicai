package com.dabaicai.common.head;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表头设置
 * @author lnb
 * @date created in 18:03 2019/5/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderCell extends Head {
  private String title;
  private String dataIndex;
  private String index;
  private String key;
  private String className;
  private int width;
  private String fixed;
  private boolean sorter;
  private boolean isCompile = false;


  public HeaderCell(String title, String dataIndex, String key, String className, int width, String fixed, boolean sorter, boolean isCompile) {
    this.title = title;
    this.dataIndex = dataIndex;
    this.key = key;
    this.className = className;
    this.width = width;
    this.fixed = fixed;
    this.sorter = sorter;
    this.isCompile = isCompile;
  }

  private HeaderCell(String title, String dataIndex, String key, String className, int width, boolean sorter, boolean isCompile) {
    this.title = title;
    this.dataIndex = dataIndex;
    this.key = key;
    this.className = className;
    this.width = width;
    this.sorter = sorter;
    this.isCompile = isCompile;
  }

  public HeaderCell(String title, String dataIndex, String key, String className, int width, boolean isCompile) {
    this.title = title;
    this.dataIndex = dataIndex;
    this.key = key;
    this.className = className;
    this.width = width;
    this.isCompile = isCompile;
  }

  public HeaderCell(String title, String dataIndex, String key, String className, String fixed, int width, boolean isCompile) {
    this.title = title;
    this.dataIndex = dataIndex;
    this.key = key;
    this.className = className;
    this.width = width;
    this.fixed = fixed;
    this.isCompile = isCompile;
  }

  public HeaderCell(String title, String dataIndex, String className, int width, String index, boolean isCompile) {
    this.title = title;
    this.dataIndex = dataIndex;
    this.key = dataIndex;
    this.className = className;
    this.width = width;
    this.index = index;
    this.isCompile = isCompile;
  }

  /**
   * 序号列
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildFirstCol() {
    return new HeaderCell("序号", HeaderUtil.FIRST_COL, HeaderUtil.FIRST_COL, HeaderUtil.CENTER, HeaderUtil.FIXED_LEFT, 60, false);
  }

  /**
   * 冻结不排序的列
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildFixedCell(String title, String dataIndex, String type, int width) {
    return new HeaderCell(title, dataIndex, dataIndex, type, "left", width, false);
  }

  /**
   * 冻结不排序的列
   * @author lnb
   * @date created in 10:52 2019/10/22
   */
  public static HeaderCell buildFixedCell(String title, String dataIndex, String type, int width, boolean isCompile) {
    return new HeaderCell(title, dataIndex, dataIndex, type, "left", width, isCompile);
  }

  /**
   * 冻结排序的列
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildFixedSortCell(String title, String dataIndex, String type, int width) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width,"left", true, false);
  }

  /**
   * 冻结排序的列
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildFixedSortCell(String title, String dataIndex, String type, int width, boolean isCompile) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width,"left", true, isCompile);
  }

  /**
   * 不冻结排序
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildCellSort(String title, String dataIndex, String type, int width) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width, true, false);
  }

  /**
   * 不冻结排序
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildCellSort(String title, String dataIndex, String type, int width, boolean isCompile) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width, true, isCompile);
  }

  /**
   * 不冻结不排序
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildCell(String title, String dataIndex, String type, int width) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width, false);
  }

  /**
   * 不冻结不排序
   * @author lnb
   * @date created in 18:05 2019/5/27
   */
  public static HeaderCell buildCell(String title, String dataIndex, String type, int width, boolean isCompile) {
    return new HeaderCell(title, dataIndex, dataIndex, type, width, false, isCompile);
  }

  /**
   * 不冻结不排序 --- dataIndex
   * @author lnb
   * @date created in 18:06 2019/5/27
   */
  public static HeaderCell buildCellAndIndex(String title, String dataIndex, String index, String type, int width) {
    return new HeaderCell(title, dataIndex, type, width, index, false);
  }

  /**
   * 不冻结不排序 --- dataIndex
   * @author lnb
   * @date created in 18:06 2019/5/27
   */
  public static HeaderCell buildCellAndIndex(String title, String dataIndex, String index, String type, int width, boolean isCompile) {
    return new HeaderCell(title, dataIndex, type, width, index, isCompile);
  }
}
