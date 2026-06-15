from joblib import load

model = load(
    "models/fraud_detector.pkl"
)

def detect_fraud(
    amount,
    hour,
    customer_age,
    transactions_today
):

    prediction = model.predict(
        [[
            amount,
            hour,
            customer_age,
            transactions_today
        ]]
    )

    return prediction[0]