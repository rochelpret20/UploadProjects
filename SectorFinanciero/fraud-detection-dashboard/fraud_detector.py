import pandas as pd

HIGH_AMOUNT_THRESHOLD = 10000

def load_transactions(path):

    df = pd.read_csv(path)

    return df


def detect_high_amount(df):

    suspicious = df[
        df["amount"] > HIGH_AMOUNT_THRESHOLD
    ]

    return suspicious


def detect_night_transactions(df):

    suspicious = df[
        (df["hour"] >= 22)
        | (df["hour"] <= 5)
    ]

    return suspicious


def calculate_statistics(df):

    stats = {
        "total_transactions": len(df),
        "total_amount": df["amount"].sum(),
        "average_amount": round(
            df["amount"].mean(), 2
        ),
        "max_amount": df["amount"].max(),
        "min_amount": df["amount"].min()
    }

    return stats