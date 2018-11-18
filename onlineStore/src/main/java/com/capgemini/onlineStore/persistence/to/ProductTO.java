package com.capgemini.onlineStore.persistence.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductTO extends AbstractTO {

    @NotNull
    private String name;
    @NotNull
    @Size(min = 0, max = 100)
    private BigDecimal marge;
    @Size(min = 0, max = 1000)
    @NotNull
    private BigDecimal price;
    @NotNull
    @Size(min = 0, max = 25)
    private BigDecimal weight;

    public ProductTO() {
    }

    public ProductTO(String name, BigDecimal marge, BigDecimal price, BigDecimal weight) {
        this.name = name;
        this.marge = marge;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMarge() {
        return marge;
    }

    public void setMarge(BigDecimal marge) {
        this.marge = marge;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public static class ProductTOBuilder {
        private String name;
        private BigDecimal marge;
        private BigDecimal price;
        private BigDecimal weight;

        public ProductTOBuilder() {
        }

        public ProductTOBuilder(String name, BigDecimal marge, BigDecimal price, BigDecimal weight) {
            this.name = name;
            this.marge = marge;
            this.price = price;
            this.weight = weight;
        }

        public ProductTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductTOBuilder withMarge(BigDecimal marge) {
            this.marge = marge;
            return this;
        }

        public ProductTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductTOBuilder withWeight(BigDecimal weight) {
            this.weight = weight;
            return this;
        }

        public ProductTO build() {
            return new ProductTO(name, marge, price, weight);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTO productTO = (ProductTO) o;
        return Objects.equals(name, productTO.name) &&
                Objects.equals(marge, productTO.marge) &&
                Objects.equals(price, productTO.price) &&
                Objects.equals(weight, productTO.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marge, price, weight);
    }

    @Override
    public String toString() {
        return "ProductTO{" +
                "name='" + name + '\'' +
                ", marge=" + marge +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
