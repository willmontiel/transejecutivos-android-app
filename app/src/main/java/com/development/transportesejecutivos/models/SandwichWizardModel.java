package com.development.transportesejecutivos.models;

import android.content.Context;
import com.development.transportesejecutivos.wizard.models.AbstractWizardModel;
import com.development.transportesejecutivos.wizard.models.BranchPage;
import com.development.transportesejecutivos.wizard.models.CustomerInfoPage;
import com.development.transportesejecutivos.wizard.models.MultipleFixedChoicePage;
import com.development.transportesejecutivos.wizard.models.PageList;
import com.development.transportesejecutivos.wizard.models.SingleFixedChoicePage;

public class SandwichWizardModel extends AbstractWizardModel {
    private Context context;
    public SandwichWizardModel(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(
                new BranchPage(this, "Destino", context)
                        .addBranch("Sandwich", new SingleFixedChoicePage(this, "Bread", context),

                                new MultipleFixedChoicePage(this, "Meats", context)
                                        .setChoices("Pepperoni", "Turkey", "Ham", "Pastrami",
                                                "Roast Beef", "Bologna"),

                                new MultipleFixedChoicePage(this, "Veggies", context)
                                        .setChoices("Tomatoes", "Lettuce", "Onions", "Pickles",
                                                "Cucumbers", "Peppers"),

                                new MultipleFixedChoicePage(this, "Cheeses", context)
                                        .setChoices("Swiss", "American", "Pepperjack", "Muenster",
                                                "Provolone", "White American", "Cheddar", "Bleu"),

                                new BranchPage(this, "Toasted?", context)
                                        .addBranch("Yes",
                                                new SingleFixedChoicePage(this, "Toast time", context)
                                                        .setChoices("30 seconds", "1 minute",
                                                                "2 minutes"))
                                        .addBranch("No")
                                        .setValue("No"))

                        .addBranch("Salad", new SingleFixedChoicePage(this, "Salad type", context)
                                        .setChoices("Greek", "Caesar")
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "Dressing", context)
                                        .setChoices("No dressing", "Balsamic", "Oil & vinegar",
                                                "Thousand Island", "Italian")
                                        .setValue("No dressing")
                        )

                        .setRequired(true),

                new CustomerInfoPage(this, "Your info").setRequired(true)
        );
    }
}
