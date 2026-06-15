import pandas as pd

from sklearn.ensemble import IsolationForest

from joblib import dump

df = pd.read_csv(
    "data/transactions.csv"
)

X = df[
    [
        "amount",
        "hour",
        "customer_age",
        "transactions_today"
    ]
]

model = IsolationForest(
    contamination=0.3,
    random_state=42
)

model.fit(X)

dump(
    model,
    "models/fraud_detector.pkl"
)

print("Fraud model trained")