package com.ifmo.lesson2;

public class LuckyTickets {
    /*
    В городе N проезд в трамвае осуществляется по бумажным отрывным билетам. Каждую
    неделю трамвайное депо заказывает в местной типографии рулон билетов с номерами от
    000001 до 999999. «Счастливым» считается билетик у которого сумма первых трёх цифр
    номера равна сумме последних трёх цифр, как, например, в билетах с номерами 003102 или
    567576. Трамвайное депо решило подарить сувенир обладателю каждого счастливого билета
    и теперь раздумывает, как много сувениров потребуется. С помощью программы подсчитайте
    сколько счастливых билетов в одном рулоне?
     */
    public static void main(String[] args) {
        System.out.println(luckyTickets());
    }

    public static int luckyTickets() {
        // TODO implement
        int count = 0;
        for (int i = 1; i < 1000000; i++) {
            int left = i / 1000;
            int right = i % 1000;
            int sumLeft = 0;
            int sumRight = 0;

            do {
                sumLeft += left % 10;
                left /= 10;
            } while (left > 0);

            do {
                sumRight += right % 10;
                right /= 10;
            } while (right > 0);

            if (sumLeft == sumRight) {
                count++;
            }
        }
        return count;
    }
}
