package PRO_Problems.Max_Puddles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserSolution {
    // tankType hash -> [column positions where it can be placed]
    int VALLEY_WIDTH;
    HashMap<Long, ArrayList<Integer>> tanksPositionsOfSize3;
    HashMap<Long, ArrayList<Integer>> tanksPositionsOfSize4;
    HashMap<Long, ArrayList<Integer>> tanksPositionsOfSize5;

    int[] valleyHeights;

    public void init(int width, int[] valleyHeights) {
        VALLEY_WIDTH = width;
        this.valleyHeights = valleyHeights;
        tanksPositionsOfSize3 = new HashMap<>();
        tanksPositionsOfSize4 = new HashMap<>();
        tanksPositionsOfSize5 = new HashMap<>();
        computeHashPositions();
    }

    void computeHashPositions(){
        for(int i=3;i<=5;i++){
            // i->tank width
            switch (i) {
                case 3:
                    computeHashPositionsForTankWidth(i, tanksPositionsOfSize3);
                    break;
                case 4:
                    computeHashPositionsForTankWidth(i, tanksPositionsOfSize3);
                    break;
                case 5:
                    computeHashPositionsForTankWidth(i, tanksPositionsOfSize3);
                    break;
        
                default:
                    break;
            }
        }
    }

    void computeHashPositionsForTankWidth(int tankWidth, HashMap<Long, ArrayList<Integer>> tanksPositions){
        for(int i=0;i<VALLEY_WIDTH-tankWidth;i++){
            int max = Arrays.stream(valleyHeights, i, i+tankWidth).max().getAsInt();
            int powerOf151 = 0;
            long hash = 0;
            for(int j=0;j<tankWidth;j++, powerOf151++){
                int delta = max - valleyHeights[i+j] + 1;
                hash += delta * Math.pow(151, powerOf151);
            }
            // System.out.println("tank hash: "+hashString);
            if(!tanksPositions.containsKey(hash)){
                tanksPositions.put(hash, new ArrayList<>());
            }
            tanksPositions.get(hash).add(i);
            // System.out.println("tanks: "+tanksPositions.get(hashString));
        }
    }

    long getTankHash(int tankWidth, int[] tankUnitHeights){
        int min = Arrays.stream(valleyHeights, 0, tankWidth).min().getAsInt();
        int powerOf151 = 0;
        long hash = 0;
        for(int j=0;j<tankWidth;j++, powerOf151++){
            int delta = tankUnitHeights[j] - min + 1;
            hash += delta * Math.pow(151, powerOf151);
        }
        return hash;
    }

    int countPositions(int tankWidth, int[] tankUnitHeights){
        long hash = getTankHash(tankWidth, tankUnitHeights);
        ArrayList<Integer> positions = null;
        switch (tankWidth) {
            case 3:
                positions = tanksPositionsOfSize3.get(hash);
                break;
            case 4:
                positions = tanksPositionsOfSize4.get(hash);
                break;
            case 5:
                positions = tanksPositionsOfSize5.get(hash);
                break;
            default:
                break;
        }
        if(positions == null){
            return 0;
        }
        return positions.size();
    }

    int buildPuddleAndPourWater(int tankWidth, int[] tankUnitHeights, int water){
        long tankHash = getTankHash(tankWidth, tankUnitHeights);
        ArrayList<Integer> tankPositions = null;
        switch (tankWidth) {
            case 3:
            tankPositions = tanksPositionsOfSize3.get(tankHash);
                break;
            case 4:
            tankPositions = tanksPositionsOfSize4.get(tankHash);
                break;
            case 5:
            tankPositions = tanksPositionsOfSize5.get(tankHash);
                break;
            default:
                return 0;
        }
        int maxPuddleSize = 0;
        for(Integer tankColumnPosition : tankPositions){
            tempFixTank(tankColumnPosition, tankUnitHeights, true);
            int leftPuddleSize = 0;
            if(tankColumnPosition >= 1 && valleyHeights[tankColumnPosition] > valleyHeights[tankColumnPosition-1]){
                leftPuddleSize = buildLeftPuddle(tankColumnPosition, water);
            }

            int rightPuddleSize = 0;
            if(tankColumnPosition+tankWidth < VALLEY_WIDTH && valleyHeights[tankColumnPosition] > valleyHeights[tankColumnPosition+tankWidth]){
                rightPuddleSize = buildRightPuddle(tankColumnPosition+tankWidth-1, water);
            }
            maxPuddleSize = Math.max(Math.max(leftPuddleSize, rightPuddleSize), maxPuddleSize);
            tempFixTank(tankColumnPosition, tankUnitHeights, false);
        }
        return maxPuddleSize;
    }

    private int buildLeftPuddle(Integer tankColumnPosition, int water) {
        int l=tankColumnPosition-1;
        while (l >= 0 && valleyHeights[l] <= valleyHeights[l+1]){
            l--;
        }
        int localLowestPosition = l+1;
        while (l >= 0 && valleyHeights[l] > valleyHeights[l+1] && valleyHeights[l] < valleyHeights[tankColumnPosition]) {
            l--;
        }
        int start = valleyHeights[l] <= valleyHeights[l+1] ? l+1 : l;
        int bottom = valleyHeights[localLowestPosition];
        int availableWater = water;
        int top = Math.min(valleyHeights[start], valleyHeights[tankColumnPosition]);
        int[] area = new int[tankColumnPosition+1-start];
        System.arraycopy(valleyHeights, tankColumnPosition-area.length, area, 0, area.length);
        while(bottom < top){
            // check pouring water
            int remainingWater = fillLayer(start, tankColumnPosition, localLowestPosition, availableWater, area, top);
            if(remainingWater == 0){
                return water;
            } else if(availableWater == remainingWater){
                return water - availableWater;
            }
            availableWater = remainingWater;
            bottom++;
        }
        return water - availableWater;
    }

    private int buildRightPuddle(int tankColumnPosition, int water) {
        int r=tankColumnPosition+1;
        while (r < VALLEY_WIDTH && valleyHeights[r] <= valleyHeights[r-1]){
            r++;
        }
        int localLowestPosition = r-1;
        while (r < VALLEY_WIDTH && valleyHeights[r] > valleyHeights[r-1] && valleyHeights[r] < valleyHeights[tankColumnPosition]) {
            r++;
        }
        int end = valleyHeights[r] <= valleyHeights[r-1] ? r-1 : r;
        int bottom = valleyHeights[localLowestPosition];
        int availableWater = water;
        int top = Math.min(valleyHeights[end], valleyHeights[tankColumnPosition]);
        int[] area = new int[end - tankColumnPosition+1];
        System.arraycopy(valleyHeights, tankColumnPosition, area, 0, area.length);
        while(bottom < top){
            // check pouring water
            int remainingWater = fillLayer(tankColumnPosition, end, localLowestPosition, availableWater, area, top);
            if(remainingWater == 0){
                return water;
            } else if(availableWater == remainingWater){
                return water - availableWater;
            }
            availableWater = remainingWater;
            bottom++;
        }
        return water - availableWater;
    }

    private int fillLayer(int start, int end, int lowestPosition, int availableWater, int[] filledArea, int topLayer) {
        int l = lowestPosition-1,r = lowestPosition;
        int requiredWater = 0;
        while(l > start && filledArea[l-start] <= topLayer){
            requiredWater++;
            // area[l-start]++;
            l--;
        }
        while(r < end && filledArea[r-start] <= topLayer){
            requiredWater++;
            // area[r-start]++;
            r++;
        }
        if(availableWater < requiredWater){
            // l = lowestPosition-1;
            // r = lowestPosition;
            // while(l > start && valleyHeights[l] == valleyHeights[lowestPosition]){
            //     requiredWater--;
            //     // area[l-start]--;
            //     l--;
            // }
            // while(r < end && valleyHeights[r] == valleyHeights[lowestPosition]){
            //     requiredWater--;
            //     // area[r-start]--;
            //     r++;
            // }
            return availableWater;
        }
        return availableWater - requiredWater;
    }

    void tempFixTank(int columnPosition, int[] tankUnitHeights, boolean apply){
        for(int i=0;i<tankUnitHeights.length;i++){
            valleyHeights[columnPosition+i] += (apply ? 1 : -1) * tankUnitHeights[i];
        }
    }
}
