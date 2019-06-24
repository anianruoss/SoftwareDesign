package jdraw.figures;

import jdraw.framework.DrawToolFactory;

public abstract class AbstractDrawToolFactory implements DrawToolFactory {
    private String name;
    private String iconName;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIconName() {
        return iconName;
    }

    @Override
    public void setIconName(String name) {
        this.iconName = name;
    }
}
