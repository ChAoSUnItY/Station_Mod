package com.noto0648.stations.client.gui;

import java.util.Random;

public class GuiTetris extends GuiScreenBase {
  private final int BLOCK_SIZE = 8;
  
  private final int BOARD_WIDTH = 12;
  
  private final int BOARD_HEIGHT = 22;
  
  private final Random rnd = new Random();
  
  private int[][] board = new int[22][12];
  
  private static int[] colors = new int[] { -16119286, -4144960, -65536, -256, -65281, -16711936, -16776961, -32768, -16711681 };
  
  private static int[][][] blocks = new int[][][] { { { -1, 0 }, { 1, 0 }, { 2, 0 } }, { { -1, -1 }, { -1, 0 }, { 0, -1 } }, { { -1, 0 }, { 0, -1 }, { 1, -1 } }, { { -1, -1 }, { 0, -1 }, { 1, 0 } }, { { -1, 0 }, { 1, 0 }, { 1, 1 } }, { { -1, 0 }, { -1, 1 }, { 1, 0 } }, { { -1, 0 }, { 0, -1 }, { 1, 0 } } };
  
  private static int[] blockMaxRotate = new int[] { 2, 1, 2, 2, 4, 4, 4 };
  
  private int currentBlockId;
  
  private int currentBlockX;
  
  private int currentBlockY;
  
  private int currentBlockRotate;
  
  private int downerCounter;
  
  private int gameScene = 1;
  
  public GuiTetris() {
    int i;
    for (i = 0; i < 12; i++)
      this.board[21][i] = 1; 
    for (i = 0; i < 22; i++) {
      this.board[i][0] = 1;
      this.board[i][11] = 1;
    } 
    newBlock();
  }
  
  protected void paint(int mouseX, int mouseY) {
    int l = (this.width - 96) / 2;
    int k = (this.height - 176) / 2;
    for (int y = 0; y < 22; y++) {
      for (int x = 0; x < 12; x++)
        drawRect(x * 8 + l, y * 8 + k, x * 8 + l + 8 - 1, y * 8 + k + 8 - 1, colors[this.board[y][x]]); 
    } 
    if (this.gameScene == 2)
      getFontRenderer().drawStringWithShadow("GAME OVER", this.width / 2 - getFontRenderer().getStringWidth("GAME OVER") / 2, this.height / 2 - 8, 16777215); 
  }
  
  protected void resize() {}
  
  public void updateScreen() {
    super.updateScreen();
    if (this.gameScene != 1)
      return; 
    this.downerCounter++;
    if (this.downerCounter == 10) {
      this.downerCounter = 0;
      downBlock();
    } 
  }
  
  protected void keyTyped(char p_73869_1_, int p_73869_2_) {
    super.keyTyped(p_73869_1_, p_73869_2_);
    if (this.gameScene == 1) {
      if (p_73869_2_ == 200) {
        putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, -2, this.currentBlockRotate);
        for (int i = 0; i < 4; i++) {
          this.currentBlockRotate++;
          this.currentBlockRotate %= blockMaxRotate[this.currentBlockId];
          if (putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate, false)) {
            putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
            break;
          } 
        } 
      } 
      if (p_73869_2_ == 208)
        downBlock(); 
      if (p_73869_2_ == 205) {
        putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, -2, this.currentBlockRotate);
        this.currentBlockX++;
        if (putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate, false)) {
          putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
        } else {
          this.currentBlockX--;
          putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
        } 
      } 
      if (p_73869_2_ == 203) {
        putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, -2, this.currentBlockRotate);
        this.currentBlockX--;
        if (putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate, false)) {
          putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
        } else {
          this.currentBlockX++;
          putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
        } 
      } 
    } 
  }
  
  private void gameOver() {
    this.gameScene = 2;
    for (int y = 0; y < 22; y++) {
      for (int x = 0; x < 12; x++) {
        if (this.board[y][x] != 0)
          this.board[y][x] = 1; 
      } 
    } 
  }
  
  private boolean downBlock() {
    putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, -2, this.currentBlockRotate);
    this.currentBlockY++;
    if (putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate, false)) {
      putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
      return true;
    } 
    this.currentBlockY--;
    putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
    for (int y = 20; y > 0; y--) {
      boolean all = true;
      for (int x = 1; x < 11; x++) {
        if (this.board[y][x] == 0) {
          all = false;
          break;
        } 
      } 
      if (all) {
        for (int _y = y; _y > 1; _y--) {
          for (int j = 1; j < 11; j++)
            this.board[_y][j] = this.board[_y - 1][j]; 
        } 
        for (int i = 1; i < 11; i++)
          this.board[1][i] = 0; 
        y++;
      } 
    } 
    if (!newBlock()) {
      gameOver();
      return false;
    } 
    return false;
  }
  
  private boolean newBlock() {
    this.currentBlockId = this.rnd.nextInt(blocks.length);
    this.currentBlockX = 5;
    this.currentBlockY = 2;
    this.currentBlockRotate = 0;
    if (putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate, false)) {
      putBlock(this.currentBlockX, this.currentBlockY, this.currentBlockId, this.currentBlockRotate);
      return true;
    } 
    return false;
  }
  
  private boolean putBlock(int x, int y, int blockId, int putBlock, int rotate) {
    return putBlock(x, y, blockId, putBlock, rotate, true);
  }
  
  private boolean putBlock(int x, int y, int blockId, int putBlock, int rotate, boolean put) {
    if (putBlock != -2 && this.board[y][x] != 0)
      return false; 
    if (put)
      this.board[y][x] = putBlock + 2; 
    for (int i = 0; i < (blocks[blockId]).length; i++) {
      int _x = blocks[blockId][i][0];
      int _y = blocks[blockId][i][1];
      for (int j = 0; j < rotate; j++) {
        int x2 = _x;
        _x = -_y;
        _y = x2;
      } 
      if (putBlock != -2 && this.board[_y + y][_x + x] != 0)
        return false; 
      if (put)
        this.board[_y + y][_x + x] = putBlock + 2; 
    } 
    return true;
  }
  
  private boolean putBlock(int x, int y, int blockId, int rotate) {
    return putBlock(x, y, blockId, blockId, rotate, true);
  }
  
  private boolean putBlock(int x, int y, int blockId, int rotate, boolean put) {
    return putBlock(x, y, blockId, blockId, rotate, put);
  }
}
