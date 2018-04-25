package test.syntax.tenum;

public enum CustomMethodEnum {

    ADD(3,3){
        @Override
        public int getCalculateResult() {
            return getBase()+getBeCalc();
        }
    },MINUS(3,3){
        @Override
        public int getCalculateResult() {
            return getBase()-getBeCalc();
        }
    },
    MULTIPLY(3,3){
        @Override
        public int getCalculateResult() {
            return getBase()*getBeCalc();
        }
    },
    DEFAULT(3,3);


    private int base;
    private int beCalc;
    CustomMethodEnum(int base,int beCalc){
        this.base = base;
        this.beCalc = beCalc;
    }

    public int getCalculateResult(){
        return this.base/beCalc;
    }

    public int getBase() {
        return base;
    }

    public int getBeCalc() {
        return beCalc;
    }
}
