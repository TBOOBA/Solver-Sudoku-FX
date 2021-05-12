package helpers;

import model.Grid;

public class CheckerFactory {
	
		private static CheckerFactory instance;
		private Grid deedCopyGrid;
		private Grid originalGrid;
		private  Object ok = new Object();
		
		private CheckerFactory(Grid grid) {
			this.deedCopyGrid = grid.deepCopy(grid);
			this.originalGrid = grid;
			this.SolveGrid();
		}
		public Grid getDeepCopy() {
			return deedCopyGrid;
		}
		public static CheckerFactory getInstance(Grid grid) {
			if(instance == null) {
				instance = new CheckerFactory(grid);
				
			}
			return instance;
		}
		
		private void SolveGrid() {
			synchronized (ok) {
				 new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							GridHelper.Solve(deedCopyGrid);
						}
					}).start();
		
			}
			
			
		}
		public boolean Check(int x, int y) {
			synchronized (ok) {
			
				
				if(originalGrid.getCellAt(x, y).getValue().equals(deedCopyGrid.getCellAt(x, y).getValue())) {
					
					return true;
				}else  return false;
				
			}
			}
		}
		
		

