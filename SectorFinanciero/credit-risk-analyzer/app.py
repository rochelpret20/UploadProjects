#streamlit run app.py
import streamlit as st
import pandas as pd
import plotly.express as px

from model import predict_risk

st.set_page_config(
    page_title="Credit Risk Analyzer",
    layout="wide"
)

st.title("Credit Risk Analyzer")

st.sidebar.header(
    "Customer Information"
)

income = st.sidebar.number_input(
    "Monthly Income",
    value=3000
)

age = st.sidebar.number_input(
    "Age",
    value=30
)

debt = st.sidebar.number_input(
    "Debt",
    value=500
)

credit_score = st.sidebar.number_input(
    "Credit Score",
    value=650
)

if st.sidebar.button(
    "Analyze Risk"
):

    prediction, probability = predict_risk(
        income,
        age,
        debt,
        credit_score
    )

    if prediction == 1:

        st.error(
            "High Risk Customer"
        )

    else:

        st.success(
            "Low Risk Customer"
        )

    st.subheader(
        "Prediction Probabilities"
    )

    risk_df = pd.DataFrame(
        {
            "Class":
            [
                "Default",
                "No Default"
            ],

            "Probability":
            [
                probability[1],
                probability[0]
            ]
        }
    )

    fig = px.bar(
        risk_df,
        x="Class",
        y="Probability",
        title="Risk Probability"
    )

    st.plotly_chart(
        fig,
        use_container_width=True
    )

st.divider()

st.header("Credit Risk Factors")

risk_factors = pd.DataFrame({

    "Factor":[
        "Income",
        "Age",
        "Debt",
        "Credit Score"
    ],

    "Importance":[
        35,
        15,
        25,
        25
    ]
})

fig2 = px.pie(
    risk_factors,
    names="Factor",
    values="Importance",
    title="Risk Factors"
)

st.plotly_chart(
    fig2,
    use_container_width=True
)