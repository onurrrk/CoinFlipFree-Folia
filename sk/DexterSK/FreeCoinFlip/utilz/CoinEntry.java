/*    */ package sk.DexterSK.FreeCoinFlip.utilz;
/*    */ 
/*    */ public class CoinEntry {
/*    */   private double amount;
/*    */   
/*    */   private boolean side;
/*    */   
/*    */   public CoinEntry(double amount, boolean side) {
/*  9 */     this.amount = amount;
/* 10 */     this.side = side;
/*    */   }
/*    */   
/*    */   public double getAmount() {
/* 14 */     return this.amount;
/*    */   }
/*    */   
/*    */   public boolean getSide() {
/* 18 */     return this.side;
/*    */   }
/*    */   
/*    */   public void setAmount(double amount) {
/* 22 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   public void setSide(boolean side) {
/* 26 */     this.side = side;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\FreeCoinFlip-Folia.jar!\sk\DexterSK\FreeCoinFli\\utilz\CoinEntry.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */